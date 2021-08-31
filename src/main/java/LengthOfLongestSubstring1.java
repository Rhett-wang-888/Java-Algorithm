import java.util.HashSet;
import java.util.Set;

/**
 * @author Rhett
 * @title: LengthOfLongestSubstring1
 * @description: TODO 无重复字符的最长字符串
 * @date 2021/3/28 9:07
 */
public class LengthOfLongestSubstring1 {
    public static void main(String[] args) {


    }
    private static int LengthOfLongestSubstring(String s){
        Set<Character> occ= new HashSet<Character>();
        int n=s.length();
        int rk =-1,ans=0;

        for(int i=0;i<n;++i){
            if(i  !=0){
                occ.remove(s.charAt(i-1));
            }
            while(rk+1<n && !occ.contains(s.charAt(rk+1))){
                occ.add(s.charAt(rk+1));
                ++rk;
            }

            ans=Math.max(ans,rk-i+1);
        }

        return ans;
    }
    public int lengthOfLongestSubstring1(String s){
        Set<Character> sets= new HashSet<Character>();
        int n=s.length();
        int rk=-1,ans=0;

        for(int i=0;i<n;++i){
            if(i !=0){
                sets.remove(s.charAt(i-1));
            }
            while(rk+1<n && !sets.contains(s.charAt(rk+1))){
                sets.add(s.charAt(rk+1));
                ++rk;
            }
            ans=Math.max(ans,rk-i+1);
        }
        return ans;
    }
}

