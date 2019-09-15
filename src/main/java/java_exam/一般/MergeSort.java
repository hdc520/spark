package java_exam.一般;

public class MergeSort {
    public static void main(String[] args) {
        int a[]={9,8,7,6,5,4,3,2,1,0};
        sort(a,0,a.length-1);
        for(int i:a){
            System.out.print(i+" ");
        }
    }
    public static void sort(int a[],int left,int right){
        if(left==right)
            return;
        int mid=(left+right)/2;
        sort(a,left,mid);
        sort(a,mid+1,right);
        merge(a,left,right);
    }
    public static void merge(int a[],int left,int right){
        int mid=(left+right)/2;
        int i=left;
        int j=mid+1;
        int count=0;
        int []tmp=new int[right-left+1];
        while (i<=mid&&j<=right){
            if(a[i]<a[j]){
                tmp[count++]=a[i++];
            }
            else {
                tmp[count++]=a[j++];
            }
        }
        while (i<=mid){
            tmp[count++]=a[i++];
        }
        while (j<=right){
            tmp[count++]=a[j++];
        }
        for(i=0;i<tmp.length;i++){
            a[left+i]=tmp[i];
        }
    }
}
