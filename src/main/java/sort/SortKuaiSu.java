package sort;

/**
 * @author Rhett
 * @title: SortKuaiSu
 * @description: TODO 快速排序 链表
 * @date 2021/11/9 15:07
 */
public class SortKuaiSu {

    private ListNode tail =null;

    public ListNode sortList(ListNode head){
        if (head == null || head.next == null){
            return head;
        }
        int pivot =head.data;
        ListNode small= new ListNode(1),large= new ListNode(1);
        ListNode hsmall=small,hlarge=large,node=head.next;

        while(node !=null){
            int val=node.data;
            if (val<pivot){
                small.next=node;
                small=small.next;
            }else{
                large.next=node;
                large=large.next;
            }
            node=node.next;
        }
        large.next=null;
        small.next=head;
        head.next=null;

        small=sortList(hsmall.next);
        large=sortList(hlarge.next);

        head.next=large;
        return small;
    }
}
