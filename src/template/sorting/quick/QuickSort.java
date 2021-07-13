package template.sorting.quick;
import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] arr = {5,4,4,3};
        quickSort.sort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public void sort(int[] arr, int l, int r){  // l <= range <= r
        // conquer
        if(r - l < 1) return;
        int rightPartitionStart = partition(arr, l, r);
        sort(arr, l, rightPartitionStart - 1);
        sort(arr, rightPartitionStart, r);
    }

    public int partition(int[] arr ,int l, int r){ // l <= range <= r
        if(r - l < 1) return l;
        // 정렬할 arr의 크기가 1이라면 그냥 반환.

        int mid = (l + r) / 2;
        int pivot = arr[mid];

        // 포인터 만들기
        int lPtr = l;
        int rPtr = r;

        while(true){

            while(lPtr < rPtr && arr[lPtr] < pivot) lPtr++;
            while (lPtr < rPtr &&  arr[rPtr] > pivot) rPtr--;

            if(lPtr > rPtr) break;

            int temp = arr[lPtr];
            arr[lPtr] = arr[rPtr];
            arr[rPtr] = temp;

            lPtr++;
            rPtr--;
        }

        return lPtr;
    }
}
