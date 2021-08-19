package template.binary_search;

public class BinarySearch {
    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();

        int[] test_arr = {2,7,11,16,19,22,22,22,32};
        System.out.println("lower bound : " + binarySearch.lowerBound(test_arr, 22));
        System.out.println("upper bound : " + binarySearch.upperBound(test_arr, 22));

        int[] test_arr2 = {11,103,209,215};
        System.out.println("lower bound : " + binarySearch.lowerBound(test_arr2, 11));
        System.out.println("upper bound : " + binarySearch.upperBound(test_arr2, 11));
    }


    // lower bound : 삽입 되었을 때 모순이 발생하지 않는 위치의 "lower bound"
    // target 보다 작거나 같은 값중 가장 오른쪽에 있는 값.
    int lowerBound(int[] arr, int target){
        int st = 0;
        int en = arr.length;

        while(st < en){
            int mid = (st + en) / 2;
            if(arr[mid] < target){
                st = mid + 1;
            }else{
                en = mid;
            }
        }

        return st;
    }

    // upper bound : 삽입 되었을 때 모순이 발생하지 않는 위치의 "upper bound"
    // target보다 작은 값중 가장 오른쪽에 있는 값?
    int upperBound(int[] arr, int target){
        int st = 0;
        int en = arr.length;

        while(st < en){
            int mid = ( st + en ) / 2;
            if(arr[mid] <= target){
                // target과 같은 값이 있을 경우 그 값들은 내가 찾아야 할 값이 아니므로
                // 제외 시켜야 한다.
                st = mid + 1;
            }else{
                en = mid;
            }
        }

        return st;
    }

}
