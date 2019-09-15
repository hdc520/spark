package java_exam.回溯法;

public class 机器人运行范围 {
    public static void main(String[] args) {
        int threshold=18,rows=40,cols=40;
        boolean visited[]=new boolean[rows*cols];
        int count=sumCount(threshold,rows,0,cols,0,visited);
        System.out.println(count);
    }
    public static int sumCount(int threshold,int rows,int row,int cols,int col,boolean visited[]){
        int count=0;
        if(row>=0&&col>=0&&row<rows&&col<cols&&!visited[row*cols+col]&&getNum(row,col)<=threshold){
            visited[row*cols+col]=true;
            count=1+sumCount(threshold,rows,row-1,cols,col,visited)+
                    sumCount(threshold,rows,row+1,cols,col,visited)+
                    sumCount(threshold,rows,row,cols,col-1,visited)+
                    sumCount(threshold,rows,row,cols,col+1,visited);
        }
        return count;
    }
    public static int getNum(int x,int y){
        int sum=0;
        while (x>0){
            sum+=x%10;
            x=x/10;
        }
        while (y>0){
            sum+=y%10;
            y=y/10;
        }
//        System.out.println(sum+" ");
        return sum;
    }
}
