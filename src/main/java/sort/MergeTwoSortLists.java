package sort;

/**
 * @author Rhett
 * @title: MergeTwoSortLists
 * @description: TODO 合并2个有序的链表
 * @date 2021/11/1 9:59
 */
public class MergeTwoSortLists {

    //递归方法进行排序
    public ListNode mergeTwoLists(ListNode l1,ListNode l2){
        if(l1==null){
            return l2;
        }else if(l2==null){
            return l1;
        }else if(l1.data<l2.data){
            l1.next=mergeTwoLists(l1.next,l2);
            return l1;
        }else {
            l2.next=mergeTwoLists(l1,l2.next);
            return l2;
        }
    }

    //迭代的方法排序
    public ListNode mergeTwoLists1(ListNode l1,ListNode l2){
        ListNode prehead=new ListNode(-1);
         ListNode pre=prehead;
         while(l1 !=null && l2 !=null){
             if(l1.data<=l2.data){
                 pre.next=l1;
                 l1=l1.next;
             }else{
                 pre.next=l2;
                 l2=l2.next;
             }
             pre=pre.next;
         }
         pre.next=l1==null?l2:l1;

         return prehead.next;

    }
}
