package java_exam.一般;

public class Bsort {
    public static void main(String[] args) {
        int a[]={1,2,4,4,4,9,4,6,0},flag=0;
        for(int i=0;i<a.length;i++){
            flag=0;
            for(int j=0;j<a.length-1-i;j++){
                if(a[j]<a[j+1]){
                    int tmp=a[j];
                    a[j]=a[j+1];
                    a[j+1]=tmp;
                    flag=1;
                }
            }
            if(flag==0)
                break;
        }
        for(int i:a){
            System.out.print(i+" ");
        }
    }
}
