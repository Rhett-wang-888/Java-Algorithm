import java.util.HashMap;
import java.util.Map;

/**
 * @author Rhett
 * @title: TwoSum
 * @description: TODO 简单的题型 2个数之和
 * @date 2021/8/30 11:15
 */
public class TwoSum {
    public int[] twoSum(int[] sums,int target){
        int n =sums.length;
        for(int i=0;i<n;++i){
            for(int j=i+1;j<n;++j){
                if(sums[i]+sums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return new int[0];
    }

    public int[] twoSum1(int[] sums,int target){
        Map<Integer,Integer> hashs= new HashMap<Integer, Integer>();
        for(int i=0;i<sums.length;++i){
           if(hashs.containsKey(target-sums[i])){
               return new int[]{hashs.get(target-sums[i]),i};
           }
           hashs.put(sums[i],i);
        }
        return new int[0];
    }
}
