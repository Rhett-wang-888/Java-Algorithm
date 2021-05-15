import java.util.List;

/**
 * @author Rhett
 * @title: ReverseLinkedList
 * @description: TODO 指定位置进行 翻转链表
 * @date 2021/5/15 13:47
 */
public class ReverseLinkedList {
    public static void main(String[] args) {

    }
    public ListNode reverseBetween (ListNode head,int left,int right){
        ListNode dummyNode= new ListNode(-1);
        dummyNode.next=head;

        ListNode pre=dummyNode;
        for(int i=0;i<left-1;i++){
            pre=pre.next;
        }

        ListNode rightNode=pre;
        for(int i=0;i<right-left+1;i++){
            rightNode=rightNode.next;
        }
        ListNode leftNode=pre.next;
        ListNode curr=rightNode.next;
        pre.next=null;
        rightNode.next=null;
        reverseLinkedList(leftNode);
        pre.next=rightNode;
        leftNode.next=curr;

        return dummyNode.next;
    }
    public void reverseLinkedList(ListNode head){
        ListNode pre=null;
        ListNode cur=head;
        while(cur!=null){
            ListNode next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
    }
    public ListNode reverseBetween1(ListNode head,int left,int right){
        ListNode dummyNode=new ListNode(-1);
        dummyNode.next=head;
        ListNode pre=dummyNode;
        for(int i=0;i<left-1;i++){
            pre=pre.next;
        }
        ListNode cur=pre.next;
        ListNode next;
        for(int i=0;i<right-left;i++){
            next=cur.next;
            cur.next=next.next;
            next.next=pre.next;
            pre.next=next;
        }
        return dummyNode.next;
    }
}
