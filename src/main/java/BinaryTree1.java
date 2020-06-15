import java.util.HashMap;

/**
 * @author Rhett.wang
 * @description: TODO 根据前序遍历和后续遍历得到tree树
 * @date 2020/6/15 16:27
 */
class TreeNode{
    int data;
    TreeNode left;
    TreeNode right;
    public TreeNode(int data){
        this.data=data;
    }
}
public class BinaryTree1 {
    public static void main(String[] args) {

    }
    HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
    int[] preorder;
    public  TreeNode buildTree(int[] preorder,int[] inorder){
        this.preorder=preorder;
        for(int i=0;i<preorder.length;i++){
            map.put(inorder[i],i);
        }
        return recursive(0,0,inorder.length-1);
    }

    private TreeNode recursive(int pre_root_idx, int in_left_idx, int in_right_idx) {
        if(in_left_idx>in_right_idx){
            return null;
        }
        TreeNode root =new TreeNode(preorder[pre_root_idx]);
        int idx =map.get(preorder[pre_root_idx]);
        root.left=recursive(pre_root_idx+1,in_left_idx,idx-1);
        root.right=recursive(pre_root_idx+(idx-1-in_left_idx+1)+1,
                idx+1,
                in_right_idx);

        return root;
    }

}
