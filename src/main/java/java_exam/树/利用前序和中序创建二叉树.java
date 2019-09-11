package java_exam.树;

import java.util.Stack;

public class 利用前序和中序创建二叉树 {
    public static void main(String[] args) {
        String treeStr="A,B,D,#,#,#,C,#,E,#,#";
        BinaryTree binaryTree=new BinaryTree(treeStr);
//        binaryTree.printInOder();//DBACE
//        binaryTree.printPostOder();//DBECA
//          binaryTree.printLevelOrder();//ABCDE
//        binaryTree.printPreOder();//ABDCE
//        binaryTree.printInOder1();
//        binaryTree.printPreOder1();
//        binaryTree.printPostOder1();
        binaryTree.printLevelOrder1();
        System.out.println("\n-------------------------");
        int[] pre = { 1, 2, 4, 7, 3, 5, 6, 8 };
        int[] in = { 4, 7, 2, 1, 5, 3, 8, 6 };
        Node root=binaryTree.reConstructBinaryTreeCore(pre, in,
                0, pre.length - 1,
                0, in.length - 1);
        Stack<Node> stack=new Stack<>();
        Node p=root;
        while(p!=null||!stack.empty()){
            if(p!=null){
                System.out.print(p.data+" ");
                stack.push(p);
                p=p.left;
            }
            else{
                p=stack.pop();
                p=p.right;
            }
        }
    }
}
