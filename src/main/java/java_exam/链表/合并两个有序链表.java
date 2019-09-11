package java_exam.链表;

public class 合并两个有序链表 {
    public static void main(String[] args) {
        createListNode c=new createListNode();
        ListNode l1=c.createOrder(4);
        ListNode l2=c.createOrder(5);
        c.output(l1);
        c.output(l2);

        ListNode s=new ListNode();
        ListNode p=new ListNode();
        System.out.println("-------------------------");
        if(l1==null) {
            c.output(l2);
            System.exit(0);
        }
        if(l2==null) {
            c.output(l1);
            System.exit(0);
        }
        s=l1.next;
        p=l2.next;
        ListNode r=new ListNode();
        l1=r;
        System.out.println("---");
        while(s!=null&&p!=null){
            System.out.println("s.val="+s.val+" p.val"+p.val);
            if(s.val<p.val){
                r.next=s;
                r=s;
                s=s.next;
            }
            else {
                r.next=p;
                r=p;
                p=p.next;
            }
        }
        System.out.println("----------");
        if(s!=null)
            r.next=s;
        if(p!=null)
            r.next=p;

        c.output(l1);

    }
}
