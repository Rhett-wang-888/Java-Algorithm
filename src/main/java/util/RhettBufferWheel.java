package util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RhettBufferWheel {
	
	private Logger logger=LoggerFactory.getLogger(RhettBufferWheel.class);
	
	//时间轮默认大小2的五次方
	private static final int STATIC_RING_SIZE=64;
	//数组作为时间轮
	private Object[] ringBuffer;
	private int bufferSize;
	//线程池
	private ExecutorService executorService;
	
	//时间轮中总任务个数
	private volatile int size =0 ;
	
	//主要确定是否继续执行触发轮询时间轮的任务，相当关闭轮询时间轮的任务
	private volatile boolean stop=false;
	//使用原子类，初始化只需要一个线程执行，确定只一次初始化启动。
	private volatile AtomicBoolean start= new AtomicBoolean(false);
	//触发任务中的表针，tick 顾名思义
	private AtomicInteger tick = new AtomicInteger();
	
	//条件锁，用于stop
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	
	//每一个任务有一个任务id
	private AtomicInteger taskId= new AtomicInteger();
	
	//用于按照taskId查找任务取消
	private Map<Integer,Task>  taskMap= new HashMap<Integer,Task>();
	
	public RhettBufferWheel(ExecutorService  executorService){
		this.executorService=executorService;
		this.bufferSize=STATIC_RING_SIZE;
		this.ringBuffer= new Object[bufferSize];
	}
	
	public RhettBufferWheel(ExecutorService executorService, int bufferSize) {
		this(executorService);
		//判断bufferSize是否是2的指数
	    if(!powerOf2(bufferSize)){
	    	throw new RuntimeException("bufferSize=[" + bufferSize + "] must be a power of 2");
	    } 
	    this.bufferSize = bufferSize;
        this.ringBuffer = new Object[bufferSize];
	}
	
	public int addTask(Task task){
		int key= task.getKey();
		int id;
        try {
            lock.lock();
            //通过key到期时间计算出index位置也就是数组位置
            int index = mod(key, bufferSize);
            logger.info("task's key = {},task's index ={}",key,index);
            task.setIndex(index);
            //查看这个数组集合之前是否有数据，因为每个数组对应一个set集合所以这里要区分
            Set<Task> tasks = get(index);

            if (tasks != null) {
                int cycleNum = cycleNum(key, bufferSize);
                task.setCycleNum(cycleNum);
                tasks.add(task);
            } else {
                int cycleNum = cycleNum(key, bufferSize);
                task.setIndex(index);
                task.setCycleNum(cycleNum);
                //如果需要重新建立set集合就要重新增加task外，还要set对应正确的数组位置。

                Set<Task> sets = new HashSet<Task>();
                sets.add(task);
                put(key, sets);
            }
            //每个任务的唯一id，统一放到hashmap中，为了查找方便，指定取消任务
            id = taskId.incrementAndGet();
            taskMap.put(id, task);
            size++;
        } finally {
            lock.unlock();
        }
        //启动时间轮
        start();

        return id;
	}
    public void stop(boolean force) {
        if (force) {
            logger.info("delay task is forced stop");
            stop = true;
            executorService.shutdownNow();
        } else {
            logger.info("delay task is stopping");
            if (taskSize() > 0) {
                try {
                    lock.lock();
                    condition.await();
                    stop = true;
                } catch (InterruptedException e) {
                    logger.error("InterruptedException", e);
                } finally {
                    lock.unlock();
                }
            }
            executorService.shutdown();
        }

    }
    /**
     * Cancel task by taskId
     * @param id unique id through {@link #addTask(Task)}
     * @return
     */
    public boolean cancel(int id) {

        boolean flag = false;
        Set<Task> tempTask = new HashSet<Task>();

        try {
            lock.lock();
            Task task = taskMap.get(id);
            if (task == null) {
                return false;
            }

            Set<Task> tasks = get(task.getIndex());
            for (Task tk : tasks) {
                if (tk.getKey() == task.getKey() && tk.getCycleNum() == task.getCycleNum()) {
                    size--;
                    flag = true;
                } else {
                    tempTask.add(tk);
                }

            }
            //update origin data
            ringBuffer[task.getIndex()] = tempTask;
        } finally {
            lock.unlock();
        }

        return flag;
    }
    /**
     * Thread safe
     *
     * @return the size of ring buffer
     */
    public int taskSize() {
        return size;
    }
    /**
     * Start background thread to consumer wheel timer, it will always run until you call method {@link #stop}
     */
    public void start() {
        if (!start.get()) {
            if (start.compareAndSet(start.get(), true)) {
                logger.info("delay task is starting");
                Thread job = new Thread(new TriggerJob());
                job.setName("consumer RingBuffer thread");
                job.start();
                start.set(true);
            }

        }
    }
    private Set<Task> remove(int key) {
        Set<Task> tempTask = new HashSet<Task>();
        Set<Task> result = new HashSet<Task>();

        Set<Task> tasks = (Set<Task>) ringBuffer[key];
        if (tasks == null) {
            return result;
        }

        for (Task task : tasks) {
            if (task.getCycleNum() == 0) {
                result.add(task);

                size2Notify();
            } else {
                // decrement 1 cycle number and update origin data
                task.setCycleNum(task.getCycleNum() - 1);
                tempTask.add(task);
            }
        }

        //update origin data
        ringBuffer[key] = tempTask;

        return result;
    }

    private Set<Task> get(int index) {
        return (Set<Task>) ringBuffer[index];
    }

    private void put(int key, Set<Task> tasks) {
        int index = mod(key, bufferSize);
        ringBuffer[index] = tasks;
    }
	
    private void size2Notify() {
        try {
            lock.lock();
            size--;
            if (size == 0) {
                condition.signal();
            }
        } finally {
            lock.unlock();
        }
    }
	
    private boolean powerOf2(int target) {
        if (target < 0) {
            return false;
        }
        int value = target & (target - 1);
        if (value != 0) {
            return false;
        }

        return true;
    }
    private int mod(int target, int mod) {
        // equals target % mod
        target = target + tick.get();
        return target & (mod - 1);
    }

    private int cycleNum(int target, int mod) {
        //equals target/mod
        return target >> Integer.bitCount(mod - 1);
    }


	public abstract static class Task extends Thread{
		//时间轮的索引位置
		private int index;
		//时间轮的圈数
		private int cycleNum;
		//时间轮延时时间。到期执行时间
		private int key;
		
		@Override
		public void run(){
			
		}
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}
		public int getCycleNum() {
			return cycleNum;
		}
		public void setCycleNum(int cycleNum) {
			this.cycleNum = cycleNum;
		}
		public int getKey() {
			return key;
		}
		public void setKey(int key) {
			this.key = key;
		}
		
		
	}
	private  class TriggerJob implements Runnable{

		public void run(){
			int index=0;
			while(!stop){
				try{
					//取出指定位置的集合，
					Set<Task> tasks=remove(index);
					for(Task task:tasks){
						//这个就是真正执行定时任务了
						executorService.submit(task);
					}
					//一个轮询
					if(++index>bufferSize-1){
						index=0;
					}
                    //Total tick number of records
                    tick.incrementAndGet();
                    TimeUnit.SECONDS.sleep(1);
				}catch(Exception e){
                    logger.error("Exception", e);
				}
			}
			logger.info("delay task is stopped");
		}
	}
	
	

}
