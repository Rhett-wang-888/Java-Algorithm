/**
 * @author Rhett
 * @title: NumOfBST
 * @description: TODO
 * @date 2020/7/15 8:52 主要是记录 1到n的数中所有的二叉搜索树的个数
 * 动态规划解法：假设n个节点存在二叉排序树的个数是 G(N)，令F(i)以i为根 的二叉搜索树的个数。
 * G(n)=f(1)+f(2)+f(3)+f(4)+...+f(n)
 * 当i为根节点时，其左子树节点个数为i-1个，右子树节点为n-i，则
 * f(i) = G(i-1)*G(n-i)f(i)=G(i−1)∗G(n−i)
 *
 * 综合两个公式可以得到 卡特兰数 公式
 * G(n) = G(0)*G(n-1)+G(1)*(n-2)+...+G(n-1)*G(0)G(n)=G(0)∗G(n−1)+G(1)∗(n−2)+...+G(n−1)∗G(0)
 *
 */
public class NumOfBST {
    public static void main(String[] args) {

    }
    private static Integer numTree(int n){
        int[] dp=new int[n+1];
        dp[0]=1;
        dp[1]=1;
        for(int i=2;i<n+1;i++){
            for(int j=1;j<i+1;j++){
                dp[i]+=dp[j-1]*dp[i-j];

            }
        }

        return dp[n];
    }

}
