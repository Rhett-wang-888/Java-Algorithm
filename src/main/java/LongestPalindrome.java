/**
 * @author Rhett
 * @title: LongestPalindrome
 * @description: TODO 求最大回文字符串
 * @date 2021/9/1 10:18
 */
public class LongestPalindrome {
    public static void main(String[] args) {

    }
    public boolean isPalindrome(String s){
        int len=s.length();
        for(int i=0;i<len/2;i++){
            if(s.charAt(i)!=s.charAt(len-i-1)){
                return false;
            }
        }
        return true;
    }
    public String LongestPalindrome(String s){
        String ans="";
        int max=0;
        int len=s.length();

        for(int i=0;i<len;i++){
            for(int j=i+1;j<=len;j++){
                String test=s.substring(i,j);
                if(isPalindrome(test)&&test.length()>max){
                    ans=s.substring(i,j);
                    max=Math.max(max,ans.length());
                }
            }
        }
        return ans;
    }

    public String LongestPalindrome1(String s){
        if (s.equals("")) return "";
        String origin=s;
        String reverse=new StringBuffer(s).reverse().toString();
        int length=s.length();
        int[][] arr=new int[length][length];
        int maxLen=0;
        int maxEnd=0;
        for(int i=0;i<length;i++){
            for(int j=0;j<length;j++){
                if(origin.charAt(i)==reverse.charAt(j)){
                    if(i==0 ||j==0){
                        arr[i][j]=1;
                    }else{
                        arr[i][j]=arr[i-1][j-1]+1;
                    }
                }
                if(arr[i][j]>maxLen){
                    maxLen=arr[i][j];
                    maxEnd=i;
                }
            }
        }
        return s.substring(maxEnd-maxLen+1,maxEnd+1);
    }
    public  String LongestPalindrome2(String s){
        if (s.equals(""))return "";
        String origin =s;
        String reverse=new StringBuffer(s).reverse().toString();
        int length=s.length();
        int[][] arr=new int[length][length];
        int maxLen=0;
        int maxEnd=0;
        for(int i=0;i<length;i++){
            for(int j=0;j<length;j++){
                if(origin.charAt(i)==reverse.charAt(j)){
                    if(i==0 ||j==0){
                        arr[i][j]=1;

                    }else{
                        arr[i][j]=arr[i-1][j-1]+1;
                    }
                }
                //修改的地方
                if(arr[i][j]>maxLen){
                    int beforeRev=length-1-j;
                    if(beforeRev+arr[i][j]-1==i){
                        maxLen=arr[i][j];
                        maxEnd=i;
                    }
                }
            }

        }
        return s.substring(maxEnd-maxLen+1,maxEnd+1);
    }

    public String LongestPalindrome3(String s){
        if(s==null||s.length()==0){
            return "";
        }
        int strLen=s.length();
        int left=0;
        int right=0;
        int len=1;
        int maxStart=0;
        int maxLen=0;

        for(int i=0;i<strLen;i++){
            left=i-1;
            right=i+1;
            while(left>=0 &&s.charAt(left)==s.charAt(i)){
                len++;
                left--;
            }
            while(right<strLen&&s.charAt(right)==s.charAt(i)){
                len++;
                right++;
            }
            while(left>=0&&right<strLen&&s.charAt(right)==s.charAt(left)){
                len=len+2;
                left--;
                right++;
            }
            if(len>maxLen){
                maxLen=len;
                maxStart=left;
            }
            len=1;
        }
        return s.substring(maxStart+1,maxStart+maxLen+1);
    }

    public String longestPalindrome4(String s){
        if(s==null||s.length()<2) return s;
        int strLen=s.length();
        int maxStart=0;
        int maxEnd=0;
        int maxLen=0;

        boolean[][] dp=new boolean[strLen][strLen];

        for(int r=1;r<strLen;r++){
            for(int l=0;l<r;l++){
                if(s.charAt(l)==s.charAt(r)&&(r-1<=2 ||dp[l+1][r-1])){
                    dp[l][r]=true;
                    if(r-l+1>maxLen){
                        maxLen=r-l+1;
                        maxStart=1;
                        maxEnd=r;
                    }
                }
            }
        }

        return s.substring(maxStart,maxEnd+1);
    }
}
