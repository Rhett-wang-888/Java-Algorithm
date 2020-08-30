import java.util.List;

/**
 * @author Rhett.wang
 * @description: TODO 最小三角路径和 动态规划 dp[i][j]=min(dp[i+1][j],dp[i+1][j+1])+triangle[i][j]
 * @date 2020/8/30 19:35
 */

public class MinPathSum {
    public static void main(String[] args) {

    }
    public  static int minimumTotal(List<List<Integer>> triangle){
        int n =triangle.size();
        int[][] dp=new int[n+1][n+1];
        for(int i=n-1;i>=0;i--){
            for(int j=0;j<=i;j++){
                dp[i][j]=Math.min(dp[i+1][j],dp[i+1][j+1])+triangle.get(i).get(j);
            }
        }
        return dp[0][0];
    }
}
