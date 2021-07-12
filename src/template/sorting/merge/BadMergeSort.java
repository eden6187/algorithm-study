package template.sorting.merge;

import java.util.Arrays;

public class BadMergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{5,4,3,3,3,3,2,1,-1,100};

        BadMergeSort mergeSort  = new BadMergeSort();
        arr = mergeSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public int[] sort(int[] arr){
         if(arr.length <= 1) return arr; // 하나로 쪼개질때 까지 쪼갠다.

        //만약 쪼갤 것이 2개이상 남아 있다면,
        int mid = arr.length / 2;

        int[] left = Arrays.copyOfRange(arr, 0, mid);
        System.out.println(Arrays.toString(left));
        int[] leftSorted = sort(left);

        /*
         이런식으로 새로운 배열을 만드는 방법이 직관적이고 쉽지만 공간복잡도나, 시간복잡도나 좋을게 없다. Big-O로는 똑같지만,
         메모리를 거의 2배 가까이 잡아먹는다.
         */

        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        System.out.println(Arrays.toString(right));
        int[] rightSorted = sort(right);

        int[] mergedArr = mergeArr(leftSorted, rightSorted);
        System.out.println("merged! : " + Arrays.toString(mergedArr));
        return mergedArr;
    }

    int[] mergeArr(int[] left, int[] right){
        int[] mergedArr = new int[left.length + right.length];

        int leftPointer = 0;
        int rightPointer = 0;
        int mergedPointer = 0;

        while(leftPointer < left.length && rightPointer < right.length){

            int leftValue = left[leftPointer];
            int rightValue = right[rightPointer];

            if(leftValue <= rightValue){ // for-stable sort
                mergedArr[mergedPointer] = leftValue;
                leftPointer++;

            }else{
                mergedArr[mergedPointer] = rightValue;
                rightPointer++;
            }

            mergedPointer++;
        }

        while(rightPointer < right.length){
            int rightValue = right[rightPointer];
            mergedArr[mergedPointer] = rightValue;
            mergedPointer++;
            rightPointer++;
        }

        while(leftPointer < left.length){
            int leftValue = left[leftPointer];
            mergedArr[mergedPointer] = leftValue;
            mergedPointer++;
            leftPointer++;
        }

        return mergedArr;
    }
}
