package java_exam.树;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {
    private Node root = new Node();
    public Node getRoot() {
        return root;
    }

    public void printNode(Node node) {
        if (node.data == null) {
            System.out.print("");
        } else {
            System.out.print(node.data);
        }
    }

    public BinaryTree(String tree) {
            String[] treeNodes = tree.split(",");
            createTreeByRecursion(treeNodes);
    }

    private int createTreeByRecursion(Node node, String[] treeNodes, int n) {
        if ("#".equals(treeNodes[n]))
            return n + 1;
        node.data = treeNodes[n];

        node.left = new Node();
        int left = createTreeByRecursion(node.left, treeNodes, n + 1);

        node.right = new Node();
        int right = createTreeByRecursion(node.right, treeNodes, left);

        return right;
    }

    public void createTreeByRecursion(String[] treeNodes) {
        createTreeByRecursion(root, treeNodes, 0);
    }
    // 递归调用的方法,需要将root传递进去
    // 非递归遍历二叉树先序
    public void printPreOder1() {
        Stack<Node>stack=new Stack<>();
        Node p=root;
        while(p!=null||!stack.empty()){
            if(p!=null){
                printNode(p);
                stack.push(p);
                p=p.left;
            }
            else{
                p=stack.pop();
                p=p.right;
            }
        }
    }

    // 中序遍历
    public void printInOder1() {
        Stack<Node> stack=new Stack<>();
        Node rootNode=root,p;
        p=rootNode;
        while (p!=null||!stack.empty()){
            if(p!=null){
                stack.push(p);
                p=p.left;
            }
            else {
                p=stack.pop();
                printNode(p);
                p=p.right;
            }
        }

    }
    // 后序遍历
    public void printPostOder1() {
        Stack<Node> stack=new Stack<>();
        Node p=root,r=null;
        while(p!=null||!stack.empty()){
            if(p!=null){
                stack.push(p);
                p=p.left;
            }
            else{
                p=stack.peek();
                if(p.right!=null&&p.right!=r){
                    p=p.right;
                    stack.push(p);
                    p=p.left;
                }
                else{
                    p=stack.pop();
                    printNode(p);
                    r=p;
                    p=null;
                }
            }
        }
    }

    // 层序遍历 利用队列
    public void printLevelOrder1() {
        Queue<Node> queue=new LinkedList<>();
        Node p=root;
        queue.offer(p);
        while(!queue.isEmpty()){
            p=queue.poll();
            printNode(p);
            if(p.left!=null){
                queue.offer(p.left);
            }
            if(p.right!=null)
                queue.offer(p.right);
        }
    }


    ////利用先序和中序求树
    public Node reConstructBinaryTreeCore(int[] pre, int[] in,
                                          int preStart, int preEnd,
                                          int inStart, int inEnd) {
        Node tree = new Node(String.valueOf(pre[preStart]));
        tree.left = null;
        tree.right = null;
        if (preStart == preEnd && inStart == inEnd) {
            return tree;
        }
        int root = 0;
        for (root = inStart; root < inEnd; root++) {
            if (pre[preStart] == in[root]) {
                break;
            }
        }
        int leifLength = root - inStart;
        int rightLength = inEnd - root;
        if (leifLength > 0) {
            tree.left = reConstructBinaryTreeCore(pre, in,
                    preStart + 1, preStart + leifLength,
                    inStart, root - 1);
        }
        if (rightLength > 0) {
            tree.right = reConstructBinaryTreeCore(pre, in,
                    preStart + 1 + leifLength, preEnd,
                    root + 1, inEnd);
        }
        return tree;
    }

    // 树高 递归，分别求出左子树的深度、右子树的深度，两个深度的较大值+1
    public int getHeightByRecursion(Node node) {

        if (node == null) {
            return 0;
        }
        int left = getHeightByRecursion(node.left)+1;
        int right = getHeightByRecursion(node.right)+1;
        if(left>right)
            return left;
        else
            return right;
//        return Math.max(left, right);

    }

    /**
     * 为什么不直接写成调用 root,而是另写一个方法去调用呢 因为,这样可以不再为root,单独设置一个临时变量去存贮
     * 而且也固定外部调用的方法,而不用关心内部的实现
     */

    public void printHeight() {

        int height = getHeightByRecursion(root);

        System.out.print(height);
    }

    // 利用层序遍历,得到树的最大宽度
    public void printMaxWidth() {
        int front=-1,rear=-1,last=0;
        int levelNum=0;//每层节点的个数
        int maxNum=-1;//树的宽度
        int high=0;//树的高度
        Node queue[]=new Node[100];
        Node p=root;
        queue[++rear]=p;
        while(front<rear){
            p=queue[++front];
            levelNum++;
            if(p.left.data!=null){
                queue[++rear]=p.left;
            }
            if(p.right.data!=null){
                queue[++rear]=p.right;
            }
            if(front==last){
                high++;
                System.out.println(levelNum);
                if(maxNum<levelNum)
                    maxNum=levelNum;
                last=rear;
                levelNum=0;
            }
        }
        System.out.println("树的宽度为："+maxNum);
        System.out.println("树的高度为："+high);
    }

    //求叶子节点的个数
    public void printleaf() {
        int leaf = getNumleaf(root);
        System.out.print(leaf);
    }

    public int getNumleaf(Node p){
        if(p==null)
            return 0;
        if(p.left==null&&p.right==null){
            return 1;
        }
            return getNumleaf(p.left)+getNumleaf(p.right);
    }
}
