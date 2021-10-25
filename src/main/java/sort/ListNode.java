package sort;

/**
 * @author Rhett
 * @title: NodeList
 * @description: TODO
 * @date 2021/10/18 10:20
 */
public class ListNode {
    Integer data;
    ListNode next;
    public ListNode(Integer data){
        this.data=data;
    }
    public ListNode(Integer data,ListNode head){
        this.data=data;
        this.next=head;
    }
}
