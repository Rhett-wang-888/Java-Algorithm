/**
 * @author Rhett
 * @title: AddTwoNumbersLink
 * @description: TODO
 * @date 2021/1/30 16:01
 */
public class AddTwoNumbersLink {
    public static void main(String[] args) {

    }
    public static ListNode addTwoNumbers(ListNode list1,ListNode list2){
        ListNode head=null,tail = null;
        int carry =0;
        while (list1 !=null ||list2!=null){
            int n1=list1 !=null ?list1.data:0;
            int n2=list2 !=null ?list2.data:0;
            int sum =n1+n2+carry;
            if(head ==null){
                head=tail=new ListNode(sum%10);
            }else{
                tail.next= new ListNode(sum%10);
                tail=tail.next;
            }
            carry=sum/10;
            if(list1 !=null)list1=list1.next;
            if(list2 !=null)list2=list2.next;

        }
        if(carry>0){
            tail.next=new ListNode(carry);
        }
        return head;
    }
}
