package sort;

/**
 * @author Rhett
 * @title: InsertSort
 * @description: TODO 直接插入排序算法测试 https://mp.weixin.qq.com/s/T81zKDXGj3GU_LVq3vBhgw
 * @date 2022/4/6 11:40
 */
public class InsertSort {
    public static void main(String[] args) {
        int i;
        int[] a ={20,40,30,10,60,50};

        System.out.printf("before sort: ");
        for(i=0;i<a.length;i++)
            System.out.printf("%d ",a[i]);
        System.out.printf("\n");

        insertSort1(a,a.length);
        System.out.printf("after sorted: ");
        for(i=0;i<a.length;i++)
            System.out.printf("%d ",a[i]);
        System.out.printf("\n");


    }
    public static int[] insertSort1(int[] nums ,int n){
        for(int i=1;i<n;i++){
            int tmp=nums[i];
            int j=i;
            while(j>0 && nums[j-1]>tmp){
                nums[j]=nums[j-1];
                j--;
            }
            nums[j]=tmp;

        }
        return nums;
    }
    /*a 待排序的数组
    * n 数组的长度
    *
    *
    * */
    public static void insertSort(int[] a,int n){
        int i,j,k;
        //
        for(i=1;i<n;i++) {
            //为啊a[i]在前面的区间找到一个合适的位置
            for(j=i-1;j>=0;j--){
                if(a[j]<a[i]){
                    break;
                }
            }

            if(j!=i-1){
                //把比a[i]大的数后移
                int temp=a[i];
                for(k=i-1;k>j;k--){
                    a[k+1]=a[k];
                }
                //
                a[k+1]=temp;
            }
        }
    }
}
