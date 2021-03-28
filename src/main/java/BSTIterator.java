import java.util.ArrayList;
import java.util.List;

/**
 * @author Rhett
 * @title: BSTIterator
 * @description: TODO 二叉树迭代器
 * @date 2021/3/28 10:42
 */
public class BSTIterator {
    private int idx;
    private List<Integer> arr;

    public BSTIterator(TreeNode root){
        idx=0;
        arr= new ArrayList<Integer>();
        inorderTraversal(root,arr);

    }
    public int next(){
        return arr.get(idx++);
    }

    public boolean hasNext(){
        return idx <arr.size();
    }
    private void inorderTraversal(TreeNode root,List<Integer> arr){
        if(root==null){
            return;
        }

        inorderTraversal(root.left,arr);
        arr.add(root.data);
        inorderTraversal(root.right,arr);
    }

}
