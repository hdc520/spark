package java_exam.一般;

public class heapSort {
    static void downHeap(int arr[],int k,int len){
        int tmp,j;
        tmp=arr[k];
        for(int i=2*k+1;i<len;i=i*2+1){
//            System.out.println("tmp="+tmp+" k="+k);
            if(i<len-1&&arr[i]>arr[i+1]){
                i++;
            }
            if(arr[i]>tmp) {
                break;
            }
            else{
                arr[k]=arr[i];
                k=i;
            }
            arr[k]=tmp;
        }
    }
    public static void main(String[] args) {
        int a[] = {91, 17, 4, 90, 45, 65, 87, 32}, i, j,tmp;
        for (i = (a.length - 1) / 2; i >= 0; i--) {
            downHeap(a, i,a.length);
        }
        System.out.print("建成的堆为：");
        for (i=0;i<a.length;i++){
            System.out.print(a[i]+"　");
        }
        System.out.println("----------------------------");
        for(i=a.length-1;i>0;i--){
            tmp=a[i];
            a[i]=a[0];
            a[0]=tmp;
            downHeap(a,0,i);
        }
        System.out.println("堆排序结果为：");
        for (i=0;i<a.length;i++){
            System.out.print(a[i]+"　");
        }
        System.out.println("\n----------------------------");
        int cin=31;
        tmp=cin;
        cin=a[0];
        a[0]=tmp;
        for (i=0;i<a.length;i++){
            System.out.print(a[i]+"　");
        }
        System.out.println("\n----------------------------");
        downHeap(a,0,a.length);
        for (i=0;i<a.length;i++){
            System.out.print(a[i]+"　");
        }
    }
}
