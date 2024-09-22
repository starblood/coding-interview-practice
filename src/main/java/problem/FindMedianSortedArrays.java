package problem;

/**
 * Given two sorted arrays, nums1 and nums2, with sizes m and n respectively, calculate the median of the two arrays. The median should be considered as a double type.
 *
 * You need to satisfy the following two conditions:
 * 1. The runtime complexity should be O(log(m+n)).
 * 2. The space complexity should be O(1).
 *
 * Example 1
 * When the sum of the lengths of the two arrays is odd, the median can be directly extracted.
 *
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.0
 *
 * Example 2
 * When the sum of the lengths of the two arrays is even, to calculate the median, find the two values in the middle of the entire array and compute their average ( (2 + 3) / 2 = 2.5 ).
 *
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.5
 *
 * Constraints:
 * 1. nums1.length == m
 * 2. nums2.length == n
 * 3. 0 <= m <= 1000
 * 4. 0 <= n <= 1000
 * 5. 1 <= m + n <= 2000
 * 6. -10^6 <= nums1[i], nums2[i] <= 10^6
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
