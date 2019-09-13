package java_exam.数组;

public class 旋转数组 {
    public static void main(String[] args) {
        int a[]={4,5,6,7,1,2,3};
        int low=0,high=a.length-1;
        while(low!=high-1){
            int mid=(low+high)/2;
            if(a[mid]>a[low])
                low=mid;
            if(a[mid]<a[high])
                high=mid;
        }
        System.out.println(a[high]);
        int b[]={1,2,3,4,5,6,7};
        int count=0;
        while(count<3){
            int tmp=b[b.length-1];
            System.out.println("tmp="+tmp);
            for(int i=b.length-1;i>0;i--){
                b[i]=b[i-1];
            }
            b[0]=tmp;
            count++;
        }
        for(int x:b){
            System.out.print(x+" ");
        }
    }
}
