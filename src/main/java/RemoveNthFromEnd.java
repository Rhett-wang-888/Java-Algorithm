/**
 * @author Rhett
 * @title: RemoveNthFromEnd
 * @description: TODO 删除链表倒数第n个节点
 * @date 2021/5/9 15:48
 */
public class RemoveNthFromEnd {
    public static void main(String[] args) {

    }

    public ListNode removeNthFromEnd(ListNode head,int n){
        ListNode dummy =new ListNode(0);
        dummy.next=head;
        int length=getLength(head);
        ListNode cur=dummy;
        for(int i=1;i<length-n+1;++i){
            cur=cur.next;
        }
        cur.next=cur.next.next;
        ListNode ans=dummy.next;
        return ans;
    }
    public int getLength(ListNode head){
        int length=0;
        while(head !=null){
            ++length;
            head=head.next;
        }
        return length;
    }
}
