package java_exam.一般;
import java.util.Arrays;

public class quickSort3 {
    public static void main(String[] args) {
        int[] arr = {4,5,7,8,1,2,9,6,3};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序结果：" + Arrays.toString(arr));
    }
    /**
     * @param arr
     * @param left  左指针
     * @param right 右指针
     */
    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            //获取枢纽值，并将其放在当前待处理序列末尾
            dealPivot(arr, left, right);
            System.out.println("排序结果：" + Arrays.toString(arr)+"  left="+left+"  right="+right);
            //枢纽值被放在序列末尾
            int pivot = right - 1;
            //左指针
            int low = left;
            //右指针
            int high = right - 1;
            while (true) {
                while (arr[++low] < arr[pivot]) {

                }
                while (low < high && arr[--high] > arr[pivot]) {

                }
                if (low< high) {
                    swap(arr, low, high);
                } else {
                    break;
                }
            }
            if (low < right) {
                swap(arr,low, right - 1);
            }
            quickSort(arr, left, low - 1);
            quickSort(arr, low + 1, right);
        }
    }
    /**
     * 处理枢纽值
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void dealPivot(int[] arr, int left, int right) {
        int mid = (left + right) / 2;
        if (arr[left] > arr[mid]) {
            swap(arr, left, mid);
        }
        if (arr[left] > arr[right]) {
            swap(arr, left, right);
        }
        if (arr[right] < arr[mid]) {
            swap(arr, right, mid);
        }
        swap(arr, right - 1, mid);
    }
    /**
     * 交换元素通用处理
     * @param arr
     * @param a
     * @param b
     */
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
