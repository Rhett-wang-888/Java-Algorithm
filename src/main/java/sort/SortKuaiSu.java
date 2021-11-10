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

    public ListNode mergeTwoListS4(ListNode l1,ListNode l2){
        if (l1==null){
            return l2;
        }
        if(l2==null){
            return l1;
        }

        ListNode head=new ListNode(0);
        ListNode p=head;

        while(l1!=null && l2!=null){
            if(l1.data<l2.data){
                p.next=l1;
                l1=l1.next;
            }else{
                p.next=l2;
                l2=l2.next;
            }
            p=p.next;
        }

        if (l1==null)p.next=l2;
        if(l2==null) p.next=l1;

        return head.next;
    }

    public ListNode mergeTwoList4(ListNode l1,ListNode l2){
        if(l1==null && l2 ==null) return null;
        if(l1==null || l2==null)return l1==null?l2:l1;
        ListNode curr_l1=l1;
        ListNode curr_l2=l2;
        ListNode newHead=null;

        if(curr_l1.data<curr_l2.data){
            newHead=curr_l1;
            curr_l1=curr_l1.next;
        }else{
            newHead=curr_l2;
            curr_l2=curr_l2.next;
        }

        ListNode resHead=newHead;

        while(curr_l1 !=null && curr_l2 !=null){
            if(curr_l1.data<curr_l2.data){
                newHead.next=curr_l1;
                curr_l1=curr_l1.next;
            }else{
                newHead.next=curr_l2;
                curr_l2=curr_l2.next;
            }
            newHead=newHead.next;
        }

        while(curr_l1 !=null){
            newHead.next=curr_l1;
            curr_l1=curr_l1.next;
            newHead=newHead.next;
        }

        while(curr_l2 !=null){
            newHead.next=curr_l2;
            curr_l2=curr_l2.next;
            newHead=newHead.next;
        }

        return resHead;
    }
}
