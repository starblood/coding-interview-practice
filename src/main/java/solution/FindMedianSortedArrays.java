package solution;

/**
 * 두 개의 정렬된 배열 nums1 과 nums2 가 각각 크기 m 과 n 으로 주어 졌을때, 두 배열의 중앙 값을 계산 하시오.
 * 중앙 값은 double type 으로 간주 하세요.
 *
 * 아래 2개의 실행 조건을 만족 해야 합니다.
 *   1. run time complexity 는 O(log(m+n))
 *   2. run space complexity 는 O(1)
 *
 * Example 1
 *   - 2 개의 배열의 길이의 합이 홀수일 경우는 중앙 값을 바로 추출이 가능
 *
 *   Input: nums1 = [1,3], nums2 = [2]
 *   Output: 2.0
 *
 *
 * Example 2
 *   - 2 개의 배열의 길이의 합이 짝수일 경우는 중앙 값을 계산 하기 위해서,
 *   - 전체 배열 에서 중앙에 해당 하는 2 개의 값의 중앙 값을 계산 (2 + 3) / = 2.5
 *
 *   Input: nums1 = [1,2], nums2 = [3,4]
 *   Output: 2.5
 *
 * 제약 사항은 아래와 같습니다:
 *   nums1.length == m
 *   nums2.length == n
 *   0 <= m <= 1000
 *   0 <= n <= 1000
 *   1 <= m + n <= 2000
 *   -10^6 <= nums1[i], nums2[i] <= 10^6
 *
 * nums1 과 nums2 중 더 작은 array 에서만 binary search 를 수행 하여 time complexity 를 O(log(min(m,n))) 로 할 수 있음.
 * 주요 아이디어는 두 array 모두에서 작은 절반의 최대값이 큰 절반의 최소값보다 작거나 같도록 partition 지점을 찾아야 합니다.
 *   1. array 을 merge 하여 partition 을 나누는 대신, 더 작은 array 에만 집중할 수 있습니다(이 array 을 A 라고 가정).
 *   2. partition index 를 partitionA 라고 가정하면, 작은 절반이 (m + n + 1) / 2 element 를 포함한다고 명시할 수 있습니다.
 *   3. 이를 통해 partitionB 를 (m + n + 1) / 2 - partitionA 로 직접 설정할 수 있습니다.
 *   4. 따라서 두 array 의 작은 절반은 항상 총 (m + n + 1) / 2 element 를 포함합니다.
 *   5. 다음 단계는 이러한 경계 element 를 비교하는 것입니다.
 *   6. 만약 maxLeftA <= minRightB 및 maxLeftB <= minRightA 조건이 모두 성립하면, 이는 우리가 올바른 위치에서 배열을 나누었다는 것을 의미합니다.
 *     - 작은 절반은 A_left 와 B_left 의 두 부분으로 구성됩니다.
 *     - 큰 절반은 A_right 와 B_right 의 두 부분으로 구성됩니다.
 *   7. 우리는 작은 절반의 최대값을 max(A[maxLeftA], B[maxLeftB]) 로, 큰 절반의 최소값을 min(A[minRightA], B[minRightB]) 로 찾아야 합니다.
 *   8. 중앙값은 이 네 개의 경계 값과 입력 배열의 전체 길이에 따라 달라지며 상황에 따라 계산할 수 있습니다.
 *   9. 만약 maxLeftA > minRightB 이면, 이는 maxLeftA가 작은 절반에 포함되기에는 너무 크다는 것을 의미하며, 우리는 A 의 더 작은 partition 값을 찾아야 합니다.
 *   10. 반면에 minRightA 가 너무 작아서 큰 절반에 포함 되기에는 너무 작다면, 우리는 A의 더 큰 파티션 값을 찾아야 합니다.
 *
 * Complexity Analysis
 *   - m 은 array nums1 의 크기, n 은 array nums2 의 크기로 정의
 *
 *   - time complexity: O(log(min(m,n)))
 *   - 크기가 min(m,n)인 더 작은 array 에 대해 binary search 를 수행 합니다.
 *
 *   - space complexity: O(1)
 *   - 이 algorithm 은 binary search 동안 몇 가지 parameter 를 저장하고 업데이트하기 위해 일정한 양의 추가 constant 개의 space 만 필요합니다.
 */

class FindMedianSortedArrays {
    public double findMedian(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedian(nums2, nums1);
        }

        int m = nums1.length, n = nums2.length;
        int left = 0, right = m;

        while (left <= right) {
            int partitionA = (left + right) / 2;
            int partitionB = (m + n + 1) / 2 - partitionA;

            int maxLeftA = (partitionA == 0) ? Integer.MIN_VALUE : nums1[partitionA - 1];
            int minRightA = (partitionA == m) ? Integer.MAX_VALUE : nums1[partitionA];

            int maxLeftB = (partitionB == 0) ? Integer.MIN_VALUE : nums2[partitionB - 1];
            int minRightB = (partitionB == n) ? Integer.MAX_VALUE : nums2[partitionB];

            if (maxLeftA <= minRightB && maxLeftB <= minRightA) {
                if ((m + n) % 2 == 0) {
                    return (Math.max(maxLeftA, maxLeftB) + Math.min(minRightA, minRightB)) / 2f;
                } else {
                    return Math.max(maxLeftA, maxLeftB);
                }
            } else if (maxLeftA > minRightB) {
                right = partitionA - 1;
            } else {
                left = partitionA + 1;
            }
        }
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
