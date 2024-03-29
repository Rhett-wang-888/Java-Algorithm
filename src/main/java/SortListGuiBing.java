/**
 * @author Rhett
 * @title: SortListGuiBing
 * @description: TODO 链表归并排序
 * @date 2021/7/14 17:11
 */
public class SortListGuiBing {

    public ListNode sortList1(ListNode head){
        if(head !=null && head.next !=null)
            return head;
        ListNode fast=head.next,slow=head;

        while(fast !=null && fast.next !=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode tmp=slow.next;
        slow.next=null;
        ListNode   left=sortList1(head);
        ListNode right=sortList1(tmp);
        ListNode h= new ListNode(0);
        ListNode res=h;
        while(left !=null && right !=null){
            if(left.data<right.data){
                h.next=left;
                left=left.next;
            }else{
                h.next=right;
                right=right.next;
            }
            h=h.next;
        }
        h.next=left !=null ?left:right;
        return res.next;
    }
    public  ListNode sortList(ListNode head){
        return sortList(head,null);
    }

    public ListNode   sortList(ListNode head,ListNode tail){
        if(head==null){
            return head;
        }
        if(head.next==tail){
            head.next=null;
            return head;
        }
        ListNode slow=head,fast=head;
        while(fast !=tail){
            slow=slow.next;
            fast=fast.next;
            if(fast!=tail){
                fast=fast.next;
            }
        }
        ListNode mid=slow;
        ListNode list1=sortList(head,mid);
        ListNode list2=sortList(mid,tail);
        ListNode sorted=merge(list1,list2);
        return sorted;
    }
    public ListNode merge(ListNode head1,ListNode head2){
        ListNode dummyNode =new ListNode(0);
        ListNode temp=dummyNode,temp1=head1,temp2=head2;
        while(temp1 !=null && temp2 !=null){
            if(temp1.data <temp2.data){
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
        }else if(temp2 !=null){
            temp.next=temp2;
        }
        return dummyNode.next;
    }
}
