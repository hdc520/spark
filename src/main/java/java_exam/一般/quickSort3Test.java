package java_exam.一般;

public class quickSort3Test {
    public static void swap(int arr[],int i,int j){
        int tmp;
//        System.out.println("i="+i+" j="+j);
        tmp=arr[i];
        arr[i]=arr[j];
        arr[j]=tmp;
    }

    public static void threeSwap(int arr[],int low,int high){
        int mid=(low+high)/2;
        if(arr[low]>arr[mid])
            swap(arr,low,mid);
        if(arr[low]>arr[high])
            swap(arr,low,high);
        if(arr[mid]>arr[high])
            swap(arr,mid,high);
        swap(arr,mid,high-1);
    }

    public static void quickSort(int arr[],int left,int right){
        if(left<right) {
            threeSwap(arr,left,right);
            int low=left;
            int index=right-1;
            int high=right-1;
            while(low<high){
                while(low<right&&arr[++low]<arr[index]){}
                while (low<right&&arr[--high]>arr[index]){}
                if(low<high){
                    swap(arr,low,high);
                }
//                else {
//                    break;
//                }
            }
            if(low<right)
                swap(arr,low,right-1);
            quickSort(arr,left,low-1);
            quickSort(arr,low+1,right);
        }
    }

    public static void main(String[] args) {
        int arr[]={9,8,3,6,7,1,2,4,5,0,0,0,0,3,6,7,9};
        quickSort(arr,0,arr.length-1);
        System.out.println("---------------------");
        for(int i:arr)
            System.out.print(i+" ");
    }
}
