/**
 * @author Rhett.wang
 * @description: TODO 盛最多水的容器
 * @date 2020/9/19 21:12
 */

public class MaxAreaWater {
    public static void main(String[] args) {

    }
    public static int maxArea(int[] height){
        int l=0,r=height.length-1;
        int ans=0;
        while (l<r){
            int area=Math.min(height[l],height[r])*(r-l);
            ans=Math.max(ans,area);
            if(height[l]<=height[r]){
                ++l;
            }else{
                --r;
            }
        }
        return ans;
    }
}
