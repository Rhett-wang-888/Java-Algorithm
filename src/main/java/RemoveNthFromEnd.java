import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Rhett
 * @title: RemoveNthFromEnd
 * @description: TODO 删除链表倒数第n个节点
 * @date 2021/5/9 15:48
 */
public class RemoveNthFromEnd {
    public static void main(String[] args) {

    }

    public ListNode removeNthFormEnd2(ListNode head,int n){
        ListNode dummy=new ListNode(0);
        dummy.next=head;
        Deque<ListNode> stack= new LinkedList<ListNode>();
        ListNode cur=dummy;

        while(cur !=null){
            stack.push(cur);
            cur=cur.next;
        }
        for(int i=0;i<n;++i){
            stack.pop();
        }
        ListNode prev=stack.peek();
        prev.next=prev.next.next;

        ListNode ans=dummy.next;

        return ans;
    }
    public ListNode removeNthFromEnd1(ListNode head,int n){
        ListNode dummy=new ListNode(0);
        int length=getLength1(head);
        ListNode cur=dummy;

        for(int i=0;i<length-n+1;++i){
            cur=cur.next;
        }
        cur.next=cur.next.next;
        ListNode ans=dummy.next;

        return ans;
    }
    public Integer getLength1(ListNode head){
        int length=0;
        while (head!=null){
            ++length;
            head=head.next;
        }
        return 0;
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
