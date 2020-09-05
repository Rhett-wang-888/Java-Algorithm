/**
 * @author Rhett
 * @title: SwapPairs
 * @description: TODO 交换2个链表的节点
 * @date 2020/9/5 15:58
 */
public class SwapPairs {
    public static void main(String[] args) {

    }
    public static ListNode swapPairs(ListNode head){
        if((head==null)||(head.next==null))
            return head;
        ListNode firstNode =head;
        ListNode secondNode =head.next;

        firstNode.next= swapPairs(secondNode.next);
        secondNode.next=firstNode;

        return secondNode;
    }
}
