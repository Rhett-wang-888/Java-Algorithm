import java.util.ArrayList;
import java.util.List;

/**
 * @author Rhett.wang
 * @description: 二叉树转换成链表，递归
 * @date 2020/8/22 21:18
 */

public class BinaryToTreeNode {
    public static void main(String[] args) {

    }
    public void preorderTraversal(TreeNode root, List<TreeNode> list){
        if(root != null){
            list.add(root);
            preorderTraversal(root.left,list);
            preorderTraversal(root.right,list);
        }
    }
    public void flatten(TreeNode root){
        List<TreeNode> list= new ArrayList<TreeNode>();
        preorderTraversal(root,list);
        int size= list.size();
        for(int i=1;i<size;i++){
            TreeNode prev=list.get(i-1),curr=list.get(i);
            prev.left=null;
            prev.right=curr;
        }
    }
}
