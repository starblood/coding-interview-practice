package problem;

/**
 * 두 개의 정렬된 배열 nums1 과 nums2 가 각각 크기 m 과 n 으로 주어 졌을때, 두 배열의 중앙 값을 계산 하시오.
 * 중앙 값은 double type 으로 간주 하세요.
 *
 * 아래 2개의 실행 조건을 만족 해야 합니다.
 * 1. run time complexity 는 O(log(m+n))
 * 2. run space complexity 는 O(1)
 *
 * Example 1
 * 2 개의 배열의 길이의 합이 홀수일 경우는 중앙 값을 바로 추출이 가능
 *
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.0
 *
 *
 * Example 2
 * 2 개의 배열의 길이의 합이 짝수일 경우는 중앙 값을 계산 하기 위해서,
 * 전체 배열 에서 중앙에 해당 하는 2 개의 값의 중앙 값을 계산 (2 + 3) / = 2.5
 *
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.5
 *
 * 제약 사항은 아래와 같습니다:
 *   nums1.length == m
 *   nums2.length == n
 *   0 <= m <= 1000
 *   0 <= n <= 1000
 *   1 <= m + n <= 2000
 *   -10^6 <= nums1[i], nums2[i] <= 10^6
 */

class FindMedianSortedArrays {
    public double findMedian(int[] nums1, int[] nums2) {
        return 0f;
    }

    private void test_odd_length_of_two_arrays() {
        int [] nums1 = {1, 3, 6};
        int [] nums2 = {5, 8, 10, 11};

        double result = findMedian(nums1, nums2);
        System.out.printf("median = %.1f\n", result);
        double expected = 6f;
        if (result == expected) {
            System.out.println("Passed.");
        } else {
            System.out.println("Failed.");
        }
    }

    private void test_even_length_of_two_arrays() {
        int [] nums1 = {3, 5};
        int [] nums2 = {2, 4};

        double result = findMedian(nums1, nums2);
        System.out.printf("median = %.1f\n", result);
        double expected = 3.5f;
        if (result == expected) {
            System.out.println("Passed.");
        } else {
            System.out.println("Failed.");
        }
    }

    public static void main(String[] args) {
        FindMedianSortedArrays findMedianSortedArrays = new FindMedianSortedArrays();
        findMedianSortedArrays.test_odd_length_of_two_arrays();
        findMedianSortedArrays.test_even_length_of_two_arrays();
    }
}
