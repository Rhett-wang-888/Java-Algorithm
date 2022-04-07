package sort;

import com.sun.corba.se.pept.transport.ListenerThread;

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

    public ListNode mergeTwoLists6(ListNode l1,ListNode l2){
        ListNode prehead=new ListNode(-1);
        ListNode prev=prehead;
        while(l1 !=null && l2 !=null){
            if(l1.data<=l2.data){
                prev.next=l1;
                l1=l1.next;
            }else{
                prev.next=l2;
                l2=l2.next;
            }
            prev=prev.next;
        }
        prev.next=l1==null?l2:l1;

        return prehead.next;
    }
    public ListNode mergeTwoLists7(ListNode l1,ListNode l2){
        if(l1==null&& l2==null) return null;
        if(l1==null || l2 ==null)return l1==null?l2:l1;

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
            if(curr_l1.data<curr_l1.data){
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
    public ListNode mergeTwoLists9(ListNode l1,ListNode l2){
        if(l1==null)return l2;
        if(l2==null)return l1;

        ListNode dummy= new ListNode(0);
        ListNode p=dummy;

        while(l1 !=null && l2 !=null){
            if(l1.data<l2.data){
                p.next=l1;
                l1=l1.next;
            }else{
                p.next=l2;
                l2=l2.next;
            }
            p=p.next;
        }

        while(l1!=null){
            p.next=l1;
            l1=l1.next;
            p=p.next;
        }
        while(l2 !=null){
            p.next=l2;
            l2=l2.next;
            p=p.next;
        }
        return dummy.next;
    }

    public ListNode mergeTwoList10(ListNode l1,ListNode l2){
        if (l1==null)return l2;
        if(l2==null) return l1;

        ListNode dummy=new ListNode(0);
        ListNode p=dummy;

        while(l1 !=null && l2 != null){
            if(l1.data<l2.data){
                p.next=l1;
                l1=l1.next;
            }else{
                p.next=l2;
                l2=l2.next;
            }
            p=p.next;
        }
        while(l1 !=null){
            p.next=l1;
            l1=l1.next;
            p=p.next;
        }

        while(l2 !=null){
            p.next=l2;
            l2=l2.next;
            p=p.next;
        }

        return dummy.next;
    }
}
