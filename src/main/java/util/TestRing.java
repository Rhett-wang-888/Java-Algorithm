package util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestRing {

	private static Logger logger = LoggerFactory.getLogger(TestRing.class);
    /**
     * @param args
     */
    public static void main(String[] args) {
    	ExecutorService exe = Executors.newFixedThreadPool(5);
    	RhettBufferWheel wheel = new RhettBufferWheel(exe,16);
    	for(int i=0;i<15;i++){
    		RhettBufferWheel.Task task = new Job(i,"task::"+i);
    		task.setKey(i);
    		logger.info("the num is ={},cancel task={}",i,wheel.addTask(task));
    		
    	}
    	/*RingBufferWheel.Task task = new Job(3, "task :: wang");
    	task.setKey(3);
    	int cancel = wheel.addTask(task);*/
    	
    	
    	//RingBufferWheel.Task task1= new Job(15,"task ::17");
    	//task1.setKey(15);
    	//logger.info("the num is ={},cancel task={}",15,wheel.addTask(task1));
    	/*new Thread(()->{
    		boolean flag=wheel.cancel(3);
    		logger.info("cancel task={}",flag);
    		
    		//boolean flag1=wheel.cancel(7);
    		//logger.info("cancel task={}",flag1);
    	}).start();
    	*/
    	//wheel.start();
    	logger.info("task size ={}",wheel.taskSize());
    	wheel.stop(false);
    	try {
			Thread.sleep(10000);
			logger.info("it is end");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
   private static class Job extends RhettBufferWheel.Task{
	  // private org.slf4j.Logger logger = LoggerFactory.getLogger(Job.class);
    	private int num;
    	private String name;
    	 
    	public Job(int num,String name){
    		this.num= num;
    		this.name= name;
    	}
    	
    	@Override
    	public void run(){
    		Thread.currentThread().setName(name);
    		logger.info("start number={}",num);
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		logger.info("end number={}",num);
    	}
    }
}

