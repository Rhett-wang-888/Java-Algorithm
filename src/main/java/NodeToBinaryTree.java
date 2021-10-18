
/**
 * @author Rhett.wang
 * @description: TODO 有序链表转换高度平衡的二叉搜索树
 * @date 2020/7/26 20:25
 */
class ListNode{
    Integer data;
    ListNode next;
    public ListNode(Integer data){
        this.data=data;
    }
}
class Solution{
    private ListNode findMiddleElement(ListNode head){
        ListNode prePtr=null;
        ListNode slowPtr=head;
        ListNode fastPtr=head;
        while(fastPtr !=null && fastPtr.next !=null){
            prePtr=slowPtr;
            slowPtr=slowPtr.next;
            fastPtr=fastPtr.next.next;
        }
        if(prePtr !=null){
            prePtr.next=null;
        }
        return slowPtr;
    }
    public TreeNode sortedListToBST(ListNode head){
        if(head ==null){
            return null;
        }
        ListNode mid= findMiddleElement(head);
        TreeNode node =new TreeNode(mid.data);
        if(head ==mid){
            return node;
        }
        node.left=this.sortedListToBST(head);
        node.right=this.sortedListToBST(mid.next);
        return node;
    }
}
public class NodeToBinaryTree {
    public static void main(String[] args) {
        ListNode node = new ListNode(0);
        Solution so= new Solution();
        so.sortedListToBST(node);
    }
}
