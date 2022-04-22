package sort;

/**
 * @author Rhett
 * @title: ChoseSort
 * @description: TODO 选择排序的使用方法
 * @date 2022/4/21 14:26
 */
public class ChoseSort {
    public static void main(String[] args) {
        int i=0;
        int[] a ={20,40,30,60,50,10,15};
        System.out.printf("before sort: ");
        for(i=0;i<a.length;i++){
            System.out.printf("%d ",a[i]);
        }
        System.out.printf("\n");

        SelectSort(a,a.length);
        System.out.printf("after sort: ");
        for(i=0;i<a.length;i++){
            System.out.printf("%d ",a[i]);
        }
        System.out.printf("\n");

    }

    public static void SelectSort(int[] a,int n){
        int j;
        int i;
        int min;

        for(i=0; i<n;i++){
            min =i;
            for(j=i+1;j<n;j++){
                if(a[j]<a[min]){
                    min=j;
                }
            }
            if(min !=i){
                int tmp=a[i];
                a[i]=a[min];
                a[min]=tmp;
            }
        }

    }
    public static void SelectSort1(int[] a ,int num){
        int i;
        int j;
        int min;

        for(i=0;i<num;i++){
            min=i;

            for(j =i+1;j<num;j++){
                if(a[j]<a[min])
                    min=j;
            }
            if(min != i) {
                int tmp=a[i];
                a[i]=a[min];
                a[min]=tmp;
            }
        }
    }
}
