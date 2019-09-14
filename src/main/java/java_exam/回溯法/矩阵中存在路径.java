package java_exam.回溯法;

public class 矩阵中存在路径 {
    public static void main(String[] args) {
        char matrix[]={
                'a','b','t','g',
                'c','f','c','s',
                'j','d','e','h'
        };
        char str[]={'b','f','c'};
        System.out.println(haspath(matrix,3,4,str));
    }
    public static boolean haspath(char []matrix,int rows,int cols,char[] str){
        if(matrix==null||rows<0||cols<0||str==null){
            return false;
        }
        if(str.length==0)
            return true;
        boolean visited[]=new boolean[matrix.length];
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(findPath(matrix,rows,i,cols,j,0,visited,str)){
                    System.out.println(i+" yes "+j);
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean findPath(
     char[] matrix,int rows,int row,int cols,int col, int pathlength,boolean visited[],char[] str){
        if(row<0 || row>=rows || col<0 || col>=cols || str[pathlength]!=matrix[row*cols+col] || visited[row*cols+col]){
            return false;
        }
        if(str.length-1==pathlength){
            return true;
        }
        boolean haspath=false;
        if(row>=0&&row<rows&&col>=0&&col<cols &&matrix[row*cols+col]==str[pathlength]&&!visited[row*cols+col]){
            pathlength++;
            visited[row*cols+col]=true;
            haspath=findPath(matrix, rows, row-1, cols, col, pathlength, visited, str)||
                    findPath(matrix, rows, row, cols, col-1, pathlength, visited, str)||
                    findPath(matrix, rows, row+1, cols, col, pathlength, visited, str)||
                    findPath(matrix, rows, row, cols, col+1, pathlength, visited, str);
            if(!haspath) {
                pathlength--;
                visited[row*cols+col]=false;
            }
        }
        return haspath;
    }
}
