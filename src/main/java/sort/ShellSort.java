package sort;

import javax.print.attribute.standard.SheetCollate;

/**
 * @author Rhett
 * @title: ShellSort
 * @description: TODO 希尔排序的方式以及实现方法
 * @date 2022/4/11 10:08
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr={9,1,4,3,8,7,5,4,6,3,2,10};
        ShellSort shellSort=new ShellSort();
        System.out.print("before：\t\t");
        shellSort.printAll(arr);
        shellSort.shellSort(arr);
        System.out.print("after: \t\t");
        shellSort.printAll(arr);

    }
    public static int[] sortArray(int[] nums){
        for(int format=nums.length/2;format>0;format/=2){
            for(int i=format;i<nums.length;i++){
                int j=i;
                int temp=nums[j];
                if(nums[j]<nums[j-format]){
                    while(j-format>=0 && temp<nums[j-format]){
                        nums[j]=nums[j-format];
                        j-=format;
                    }
                    nums[j]=temp;
                }
            }
        }
        return nums;
    }
    public static int[] sortArray1(int[] nums){
        int n = nums.length;
        int h=n/2;
        while(h>0){
            for(int i=h;i<n;i++){
                insertSort(nums,h,i);
            }
            h/=2;
        }
        return nums;
    }

    private static void insertSort(int[] nums, int h, int i) {
        int temp=nums[i];
        int j=i;
        while(j>=h && nums[j-h]>temp){
            nums[j]=nums[j-h];
            j-=h;
        }
        nums[j]=temp;
    }

    public void shellSort(int[] list){
        int gap=list.length/2;
        while(1<=gap){
            for(int i=gap;i<list.length;i++){
                int j=0;
                int tem=list[i];

                for(j=i-gap;j>=0 && tem<list[j];j=j-gap){
                    list[j+gap]=list[j];
                }
                list[j+gap]=tem;

            }
            System.out.format("gap = %d :\t ",gap);
            printAll(list);
            gap=gap/2;

        }


    }

    private void printAll(int[] list) {
        for(int value: list){
            System.out.print(value+"\t");
        }
        System.out.println();
    }


}
