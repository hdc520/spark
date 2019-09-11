package java_exam.一般;

public class Fibonacci {
    public static void main(String[] args) {
        int a[]=new int[6];
        a[0]=a[1]=1;
        //数组求解
        for(int i=2;i<a.length;i++){
            a[i]=a[i-1]+a[i-2];
        }
        for (int i:a)
            System.out.print(i+" ");
        System.out.println("\n递归求解："+func(5));

        //循环求解：
        int x=1,y=1,z=0,n=4,i=0;
        while(i<4){
            z=x+y;
            x=y;
            y=z;
            System.out.println("循环求解：x="+x+" y="+y+" z="+z);
            i++;
        }

    }
    public static int func(int n){
        if(n<=1)
            return 1;
        else
            return func(n-1)+func(n-2);
    }
}
