/**
 * @author Rhett
 * @title: AddTwoNumbers
 * @description: TODO 链表 两个数据相加
 * @date 2021/3/13 12:01
 */
public class AddTwoNumbers {
    public static void main(String[] args) {

    }

    public static ListNode addTwoNumbers(ListNode l1,ListNode l2){
        ListNode head =null,tail=null;
        int carry=0;

        while(l1!=null || l2 !=null){
            int n1=l1!=null?l1.data:0;
            int n2=l2!=null?l2.data:0;

            int sum=n1+n2+carry;

            if(head==null){
                head=tail=new ListNode(sum%10);
            }else{
                tail.next=new ListNode(sum%10);
                tail=tail.next;
            }
            carry=sum/10;
            if(l1!=null) l1=l1.next;
            if(l2!=null) l2=l2.next;
        }
        if(carry>0){
            tail.next=new ListNode(carry);
        }

        return head;
    }
}
