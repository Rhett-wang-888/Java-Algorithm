import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Rhett
 * @title: HuiWen
 * @description: TODO 给定一个字符串，分割成回文串
 * @date 2021/3/7 14:11
 */
public class HuiWen {
    public static void main(String[] args) {

    }
    boolean f[][];
    List<List<String>> ret=new ArrayList<List<String>>();
    List<String> ans= new ArrayList<String>();
    int n;

    public List<List<String>> partition(String s){
        n=s.length();
        f= new boolean[n][n];
        for(int i=0;i<n;++i){
            Arrays.fill(f[i],true);
        }
        for(int i=n-1;i>=0;--i){
            for(int j=i+1;j<n;++j){
                f[i][j]=(s.charAt(i)==s.charAt(j)) && f[i+1][j-1];
            }
        }
        dfs(s,0);

        return ret;
    }
    private void dfs(String s,int i){
        if(i==n){
            ret.add(new ArrayList<String>(ans));
            return;
        }
        for(int j=i;j<n;++j){
            if(f[i][j]){
                ans.add(s.substring(i,j+1));
                dfs(s,j+1);
                ans.remove(ans.size()-1);
            }
        }
    }


}
