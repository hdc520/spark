package java_exam.链表;

public class 寻找链表的中间节点 {
    public static void main(String[] args) {
        createListNode create=new createListNode();
        ListNode l=create.createDisorder(8);
        create.output(l);
        System.out.print("----------------------\n");
        ListNode start,end;
        end=l.next;
        start=end;
        while(end!=null){
            if(end.next==null||end.next.next==null)
                break;
            else
            {
                end=end.next.next;
                start=start.next;
            }
        }
        System.out.println(start.toString());
    }
}
