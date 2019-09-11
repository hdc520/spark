package java_exam.一般;

public class 选择排序 {
    public static void main(String[] args) {
        int a[]={1,2,4,4,4,9,4,6,0};
        for(int i=0;i<a.length;i++){
            for(int j=i+1;j<a.length;j++){
                if(a[i]<a[j]){
                    int tmp=a[i];
                    a[i]=a[j];
                    a[j]=tmp;
                }
            }
        }
        for(int i:a){
            System.out.print(i+" ");
        }
    }
}
