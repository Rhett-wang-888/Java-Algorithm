package sort;

/**
 * @author Rhett
 * @title: GuiBingDiToShang
 * @description: TODO 归并排序链表使用自顶向上的排序的方法
 * @date 2021/10/25 10:00
 */
public class GuiBingDiToShang {
    public ListNode sortList(ListNode head){
        if (head ==null)
            return head;
        int length=0;
        ListNode node =head;
        while(node !=null){
            length++;
            node=node.next;
        }
        ListNode dummyHead= new ListNode(0,head);
        for(int subLength=1;subLength<length;subLength<<=1){
            ListNode prev =dummyHead,cur= dummyHead.next;
            while(cur!=null){
                ListNode head1=cur;
                for(int i=1;i<subLength&&cur.next!=null;i++){
                    cur=cur.next;
                }
                ListNode head2=cur.next;
                cur.next=null;
                cur=head2;
                for(int i=1;i<subLength&&cur!=null && cur.next!=null;i++){
                    cur=cur.next;
                }
                ListNode next=null;
                if(cur!=null){
                    next=cur.next;
                    cur.next=null;
                }
                ListNode merged=merge(head1,head2);
                prev.next=merged;
                while (prev.next!=null){
                    prev=prev.next;
                }
                cur=next;

            }
        }
        return dummyHead.next;
    }
    public ListNode merge(ListNode head1,ListNode head2){
        ListNode dummyHead=new ListNode(0);
        ListNode temp=dummyHead,temp1=head1,temp2=head2;
        while(temp1!=null && temp2!=null){
            if(temp1.data<=temp2.data){
                temp.next=temp1;
                temp1=temp1.next;
            }else{
                temp.next=temp2;
                temp2=temp2.next;
            }
            temp=temp.next;
        }
        if (temp1 !=null){
            temp.next=temp1;
        }else if(temp2 !=null){
            temp.next=temp2;
        }

        return dummyHead.next;
    }
}
