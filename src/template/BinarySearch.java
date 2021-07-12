package template;

public class BinarySearch {
    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int[] arr = {2,7,11,16,19,22,22,22,32};
        System.out.println("target : " + 2 + " idx : " + binarySearch.binarySearchBasic(arr, 5));
        arr = new int[]{2,7,11,16,19,22,22,22,32};

        int target = 16;
        System.out.println("target : " + target + " lowerBoundIdx : " + binarySearch.lowerBound(arr, target));
        System.out.println("target : " + target + " upperBoundIdx : " + binarySearch.upperBound(arr, target));
        target = 100;
        System.out.println("target : " + target + " lowerBoundIdx : " + binarySearch.lowerBound(arr, target));
        System.out.println("target : " + target + " upperBoundIdx : " + binarySearch.upperBound(arr, target));
        target = -1;
        System.out.println("target : " + target + " lowerBoundIdx : " + binarySearch.lowerBound(arr, target));
        System.out.println("target : " + target + " upperBoundIdx : " + binarySearch.upperBound(arr, target));

        target = 16;
        System.out.println("target : " + target + " lowerBoundUnzipped : " + binarySearch.lowerBoundUnzipped(arr, target));
        System.out.println("target : " + target + " upperBoundUnzipped : " + binarySearch.upperBoundUnzipped(arr, target));
        target = 100;
        System.out.println("target : " + target + " lowerBoundUnzipped : " + binarySearch.lowerBoundUnzipped(arr, target));
        System.out.println("target : " + target + " upperBoundUnzipped : " + binarySearch.upperBoundUnzipped(arr, target));
        target = -1;
        System.out.println("target : " + target + " lowerBoundUnzipped : " + binarySearch.lowerBoundUnzipped(arr, target));
        System.out.println("target : " + target + " upperBoundUnzipped : " + binarySearch.upperBoundUnzipped(arr, target));
    }

    // 일반적인 이분 탐색 target의 idx를 반환하지만 어떠한 idx를 반환할지는 장담 할 수 없다.
    int binarySearchBasic(int[] arr, int target){
        int st = 0;
        int en = arr.length - 1;

        while(st <= en){
            int mid = (st + en) / 2;
            if(target == arr[mid]) return mid;
            else if(target > arr[mid]){
                st = mid + 1;
            }else{
                en = mid - 1;
            }
        }

        return -1;
    }

    // 이분탐색을 이용한 lower bound 찾기
    // lower_bound의 정의 : 해당 값을 삽입 했을 경우 정렬된 순서가 유지 될 수 있는 idx 중의 "최솟값."
    // 즉, target 보다 크거나 같은 값이 처음으로 나타나는 idx
    int lowerBound(int[] arr, int target){
        int s = 0;
        int e = arr.length;

        while(s < e){
            int mid = (s+e)/2;
            if(target > arr[mid]) s = mid + 1; // 새로운 범위를 지정할 때 mid 값을 버릴지 말지를 결정해야 한다.
            // target이 중앙에 있는 값보다 클 경우 중앙 보다 하나 더 큰 곳에서 찾겠다는 의미?
            else e = mid;
        }

        return s;
    }

    int lowerBoundUnzipped(int[] arr, int target){
        int s = 0;
        int e = arr.length;

        while(s < e){
            int mid = (s+e)/2;
            if(target == arr[mid]) e = mid;
            else if(target > arr[mid]) s = mid + 1; // 새로운 범위를 지정할 때 mid 값을 버릴지 말지를 결정해야 한다.
                // target이 중앙에 있는 값보다 클 경우 중앙 보다 하나 더 큰 곳에서 찾겠다는 의미?
            else e = mid;
        }

        return s;
    }



    // 이분탐색을 이용한 upper bound 찾기
    // upper_bound의 정의 : 해당 값을 삽입 했을 경우 정렬된 순서가 유지 될 수 있는 idx 중의 "최댓값."
    // 즉, target보다 "큰" 값이 처음으로 나타나는 idx
    int upperBound(int[] arr, int target){
        int s = 0;
        int e = arr.length;

        while(s < e){
            int mid = (s+e)/2;
            if(target >= arr[mid]) s = mid + 1; // 새로운 범위를 지정할 때 mid 값을 버릴지 말지를 결정해야 한다.
            // target이 중앙에 있는 값보다 크거나 같을 경우 중앙 보다 하나 더 큰 곳에서 찾겠다는 의미?
            else e = mid;
        }

        return e;
    }

    int upperBoundUnzipped(int[] arr, int target){
        int s = 0;
        int e = arr.length;

        while(s < e){
            int mid = (s+e)/2;
            System.out.println(s + " : " +e);
            if(target == arr[mid]) s = mid + 1;
            else if(target > arr[mid]) s = mid + 1; // 새로운 범위를 지정할 때 mid 값을 버릴지 말지를 결정해야 한다.
                // target이 중앙에 있는 값보다 크거나 같을 경우 중앙 보다 하나 더 큰 곳에서 찾겠다는 의미?
            else e = mid;
        }

        return s;
    }

}
