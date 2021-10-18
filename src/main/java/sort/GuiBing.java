package sort;
/**
 * @author Rhett
 * @title: GuiBing
 * @description: TODO
 * @date 2021/10/18 10:13
 */
public class GuiBing {
    public ListNode sortList(ListNode head){
        return sortList(head,null);
    }

    private ListNode sortList(ListNode head, ListNode tail) {
        if(head==null)
            return head;
        if(head.next==tail){
            head.next=null;
            return head;
        }
        ListNode slow=head,fast=head;
        while(fast !=tail){
            slow=slow.next;
            fast=fast.next;
            if(fast!=tail)
                fast=fast.next;
        }
        ListNode mid=slow;
        ListNode list1=sortList(head,mid);
        ListNode list2=sortList(mid,tail);
        ListNode sorted=merge(list1,list2);
        return sorted;

    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummyHead=new ListNode(0);
        ListNode temp=dummyHead,temp1=head1,temp2=head2;

        while(temp1 !=null && temp2!=null){
            if(temp1.data<=temp2.data){
                temp.next=temp1;
                temp1=temp1.next;
            }else{
                temp.next=temp2;
                temp2=temp2.next;
            }
            temp=temp.next;
        }
        if(temp1!=null){
            temp.next=temp1;
        }else if(temp2!=null){
            temp.next=temp2;
        }
        return dummyHead.next;
    }
}
