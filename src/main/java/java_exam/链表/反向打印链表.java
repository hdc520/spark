package java_exam.链表;

import java.util.Stack;

public class 反向打印链表 {
    public static void main(String[] args) {
        createListNode create=new createListNode();
        ListNode l=create.createDisorder(6);
        create.output(l);
        System.out.print("----------------------\n");
        Stack stack=new Stack();
        ListNode s;
        s=l.next;
        while(s!=null){
            stack.push(s.val);
            s=s.next;
        }
        while(!stack.empty()){
            System.out.print(stack.pop()+" ");
//            stack.pop();
        }
    }
}
