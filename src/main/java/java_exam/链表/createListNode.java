package java_exam.链表;

import java.util.Random;

public class createListNode {
    //创建无序链表
    public ListNode createDisorder(int n){
        ListNode l=new ListNode();
        Random r=new Random();
        l.next=null;
        if(n==0)
            return null;
        for(int i=0;i<n;i++){
            ListNode p=new ListNode(r.nextInt(30));
            p.next=l.next;
            l.next=p;
        }
        return l;
    }
    //创建有序链表
    public ListNode createOrder(int n){
        ListNode l=new ListNode();
        Random r=new Random();
        int tmp=-999,data,count=0;
        l.next=null;
        if(n==0)
            return null;
        ListNode s = new ListNode();
        s=l;
        while (true) {
            if(count>n)
                break;
            data = r.nextInt(16+(int)(count*1.5+5))-r.nextInt(15)-5;
            if (tmp <data) {
                ListNode p = new ListNode(data);
                s.next=p;
                s=p;
                tmp=data;
                count++;
            }
        }
        return l;
    }
    public void output(ListNode l){
        if(l==null)
        {
            return;
        }
        ListNode p=new ListNode();
        p=l.next;
        while(p!=null){
            System.out.print(p.val+" -> ");
            p=p.next;
        }
        System.out.println();
    }
}
