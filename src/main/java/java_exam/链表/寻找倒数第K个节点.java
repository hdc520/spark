package java_exam.链表;
import java.util.Scanner;
public class 寻找倒数第K个节点 {
    public static void main(String[] args) {
        createListNode c=new createListNode();
        System.out.print("输入链表长度：");
        Scanner in=new Scanner(System.in);
        int n=in.nextInt();
        ListNode l=c.createDisorder(n);
        ListNode s=new ListNode();
        ListNode p=new ListNode();
        s=l.next;
        while(s!=null){
            System.out.print(s.val+"->");
            s=s.next;
        }
        System.out.println();
        //
        System.out.print("输入倒数第k个值：");
        int k=in.nextInt();
        if(k>n||k==0||n==0) {
            System.out.print("无倒数第k个节点");
            System.exit(0);
        }
        System.out.println("方法一：");
        int tmp=n-k+1;
        int count=1;
        p=l.next;
        while(count<tmp){
            p=p.next;
            count++;
        }
        System.out.println(p.toString());
        System.out.println("方法二：");
        p=l.next;
        count=0;
        while(p!=null){
            p=p.next;
            count++;
            if(count==k-1)
                s=l;
            if(count>k-1)
                s=s.next;
        }
        System.out.println(s.toString());
    }

}
