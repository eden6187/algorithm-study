package template.sorting.merge;

import java.util.Arrays;

public class GoodMergeSort {
    public static void main(String[] args) {
        GoodMergeSort gms = new GoodMergeSort();
        int[] arr = new int[]{3,3,2,-1,-10,-23,0,0,0,2,1,1};
        gms.mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void mergeSort(int[] arr){
        this.sort(arr, 0, arr.length-1);
    }

    public void sort(int[] arr, int left, int right){
        if(right - left == 0) return;
        int mid = (left + right) / 2;
        sort(arr, left, mid);
        sort(arr,mid+1, right);
        merge(arr, left, right);
    }

    public void merge(int[] src, int left, int right){
        int mid = (left + right) / 2; // left ~ mid 포함.
        int[] tmp = new int[right - left + 1];

        int leftPtr = left; // 왼쪽 절반짜리 배열의 시작점
        int rightPtr = mid + 1; // 오른 절반짜리 배열의 시작점
        int tmpPtr = 0;

        while(leftPtr < mid + 1  && rightPtr < right + 1){
            int leftValue = src[leftPtr];
            int rightValue = src[rightPtr];

            if(leftValue <= rightValue) {
                tmp[tmpPtr] = leftValue;
                leftPtr++;
            }
            else {
                tmp[tmpPtr] = rightValue;
                rightPtr++;
            }
            tmpPtr++;
        }

        while(leftPtr < mid + 1){
            tmp[tmpPtr++]  = src[leftPtr++];
        }

        while (rightPtr < right + 1){
            tmp[tmpPtr++]  = src[rightPtr++];
        }

        for(int i = 0 ; i < tmp.length; i++){
            src[left + i] = tmp[i];
        }

        return;
    }
}
