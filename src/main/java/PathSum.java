import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Rhett.wang
 * @description: TODO 二叉树路径总和等于给定值，路径必须要以叶子节点结束 回溯算法
 * @date 2020/8/2 8:09
 */

public class PathSum {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode t1=root.right= new TreeNode(3);
        TreeNode t2=root.left= new TreeNode(1);
        t1.left = new TreeNode(2);
        t1.right= new TreeNode(4);
        t2.left= new TreeNode(4);
        t2.right= new TreeNode(8);
         PathSum pathSum = new PathSum();
        List<List<Integer>> res= pathSum.pathSum(root,10);
        System.out.println(Arrays.toString(res.toArray()));

    }
    public List<List<Integer>> pathSum(TreeNode root,int sum){
        List<List<Integer>> ans= new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        pathSum(ans,path,root,0,sum);
        return ans;
    }

    private void pathSum(List<List<Integer>> ans,
                         List<Integer> path, TreeNode root,
                         int temp, int sum) {
        if(root==null){
            return;
        }
        if(root.left==null && root.right==null && temp+root.data==sum){
            path.add(root.data);
            ans.add(new ArrayList<Integer>(path));
            path.remove(path.size()-1);
            return;
        }
        path.add(root.data);
        pathSum(ans,path,root.left,temp+root.data,sum);
        pathSum(ans,path,root.right,temp+root.data,sum);
        path.remove(path.size()-1);
    }
}

