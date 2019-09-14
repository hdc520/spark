package java_exam.回溯法;

import java.util.Stack;

public class 矩阵中存在路径之栈 {
    public static void main(String[] args) {
        char matrix[]={
                'a','b','t','g',
                'c','f','c','s',
                'j','d','e','h'
        };
        char str[]={'b','f','c'};
        System.out.println(hasPath(matrix,3,4,str));
    }
    public static boolean hasPath(char[]matrix,int rows,int cols,char str[]){
        if(matrix==null||str==null||matrix.length==0)
            return false;
        if(str.length==0)
            return true;
        Stack<Integer>stack=new Stack<>();
        boolean result=false;
        boolean visited[]=new boolean[rows*cols];
        for(int i=0;i<matrix.length;i++){
//            visited[i]=false;
            if(matrix[i]==str[0]){
                stack.push(i);
                result=true;
            }
        }
        for(int j=1;j<str.length&&!stack.empty();){
            result=false;
            int index=stack.pop();
            visited[index]=true;
            if(hasNext(matrix,rows,cols,index,stack,str[j],visited)){
                if(++j==str.length)
                    result=true;
            }
        }
        return result;
    }
    public static boolean hasNext(char[]matrix,int rows,int cols,int index,Stack<Integer>stack,char c,boolean visited[]){
        int up=index-cols;
        int down=index+cols;
        int left=index-1;
        int right=index+1;
        boolean hasnext=false;
        if(up>=0&&matrix[up]==c&&!visited[up]){
            stack.push(up);
            hasnext=true;
        }
        if(down<matrix.length&&matrix[down]==c&&!visited[down]){
            stack.push(down);
            hasnext=true;
        }
        if(index%cols!=0&&matrix[left]==c&&!visited[left]){
            stack.push(left);
            hasnext=true;
        }
        if((index+1)%cols!=0&&matrix[right]==c&&!visited[right]){
            stack.push(right);
            hasnext=true;
        }
        return hasnext;
    }
}
