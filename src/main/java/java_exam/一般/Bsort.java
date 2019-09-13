package java_exam.一般;

public class Bsort {
    public static void main(String[] args) {
        int a[]={8,7,6,12,5,4,3,2,1,0},flag=0;
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
        System.out.println();
        int b[]={8,7,6,12,5,4,3,2,1,0};
        int left=0,shift=1,right=b.length-1;
        while(left<right){
            for(int i=left;i<right;i++){
                if(b[i]>b[i+1]){
                    int tmp=b[i];
                    b[i]=b[i+1];
                    b[i+1]=tmp;
                    shift=i;
                }
            }
            right=shift;
            for(int j=right-1;j>=0;j--){
                if(b[j]>b[j+1]){
                    int tmp=b[j];
                    b[j]=b[j+1];
                    b[j+1]=tmp;
                    shift=j;
                }
            }
            left=shift;
        }
        for(int i:b){
            System.out.print(i+" ");
        }
    }
}
