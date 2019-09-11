package java_exam.数组;

public class 有序数组中查找数 {
    public static void main(String[] args) {
        int a[][]={{1,2,8,9},
                  {2,4,9,12},
                  {4,7,10,13},
                  {6,8,11,15}};
        int num=1,flag=0;
        int m=3,n=3;
        int row=0;
        while(m>0&&n>0){
            if(a[row][n]>num)
                n--;
            else if(a[row][n]<num){
                row++;
            }
            else{
                System.out.println("row="+row+" cow="+n);
                flag=1;
                break;
            }
        }
        if(flag==0)
            System.out.println("二维数组中没有"+num);
    }
}
