package java_exam.一般;

public class top2 {
    public static void main(String[] args) {
        int a[]={10,10,3,7,11,10,4,9,10,9};
        int i,j,secondMax=0,max=0;
        for(i=0;i<a.length;i++){
            if(max<=a[i]){
                max=a[i];
            }
            else {
                if(a[i]>secondMax)
                    secondMax=a[i];
            }
        }
        System.out.println(max+" "+secondMax);
    }
}
