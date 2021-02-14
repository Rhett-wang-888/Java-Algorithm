import java.util.HashMap;

/**
 * @author Rhett
 * @title: LengthOfLongestSubstring
 * @description: 无重复 字符的最长字符串：滑动窗口来解决此次问题
 * @date 2021/2/14 13:40
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {

    }
    public static int LengthOfLongestSubstring(String s){
        if(s.length()==0) return 0;
        HashMap<Character,Integer> map = new HashMap<Character, Integer>();
        int max=0;
        int left=0;
        for(int i=0;i<s.length();i++){
            if(map.containsKey(s.charAt(i))){
                left=Math.max(left,map.get(s.charAt(i))+1);
            }
            map.put(s.charAt(i),i);
            max=Math.max(max,i-left+1);
        }

        return max;
    }
}
