package java_exam.链表;

public class 链表逆置 {
    //头插法
    public ListNode reverseListNode1(ListNode l){
        ListNode p,s;
        p=l.next;
        l.next=null;
        while(p!=null){
            s=p.next;
            p.next=l.next;
            l.next=p;
            p=s;
        }
        return l;
    }

        //非头插法
    public ListNode reverseListNode2(ListNode l){
        ListNode pre=null,temp;
        temp=l;
        while(temp!=null){
            temp=temp.next;
            l.next=pre;
            pre=l;
            l=temp;
        }
        return l;
    }
}
