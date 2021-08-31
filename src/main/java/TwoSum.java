import java.util.HashMap;
import java.util.Map;

/**
 * @author Rhett
 * @title: TwoSum
 * @description: TODO 简单的题型 2个数之和,中等难度就是2个链表中的数字进行相加
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

    //2个链表进行相加并且进位
    public ListNode addTwoNumbers(ListNode l1,ListNode l2){
        ListNode head=null,tail=null;
        int carry=0;
        while(l1 !=null||l2 !=null){
            int n1=l1!=null?l1.data:0;
            int n2=l2!=null?l2.data:0;

            int sum=n1+n2+carry;
            if(head==null){
                head=tail=new ListNode(sum%10);
            }else{
                tail.next= new ListNode(sum%10);
                tail =tail.next;
            }
            carry=sum/10;
            if(l1!=null){
                l1=l1.next;
            }
            if(l2!=null){
                l2=l2.next;
            }
        }
        if(carry>0){
            tail.next=new ListNode(carry);
        }
        return head;
    }



}
