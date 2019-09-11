package java_exam.一般;

public class quickSortTest {
    public static void main(String[] args) {
        int []a={31,10,87,65,45,22,67};
        sort(a,0,a.length-1);
        for(int i:a)
            System.out.print(i+" ");
    }
    public static void sort(int []a,int low,int high) {
        if (low < high) {
            int index = getIndex(a, low, high);
            sort(a, low, index - 1);
            sort(a, index + 1, high);
        }
    }
    public static int getIndex(int a[],int low,int high){
        int tmp=a[low];
        while(low<high){
            while(low<high&&tmp<a[high]) {
                high--;
            }
            a[low]=a[high];
            while(low<high&&tmp>a[low]) {
                low++;
            }
            a[high]=a[low];
        }
        a[low]=tmp;
        return low;
    }

}
























