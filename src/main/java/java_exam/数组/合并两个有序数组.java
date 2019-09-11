package java_exam.数组;

public class 合并两个有序数组 {
    public static void main(String[] args) {
        int a[]={1,3,4,6,9};
        int b[]={2,5,10};
        int length=a.length+b.length;
        int c[]=new int[length];
        int i=0,j=0,count=0;
        while(i<a.length&&j<b.length){
            if(a[i]<b[j])
                c[count++]=a[i++];
            else
                c[count++]=b[j++];
        }
        for(int x=i;x<a.length;x++)
            c[count++]=a[x++];
        for(int x=j;x<b.length;x++)
            c[count++]=b[x++];
        for(int y:c){
            System.out.print(y+" ");
        }
    }
}
