package problem;

/**
 * Find the longest palindrome substring from the given string input.
 * Palindrome: A word or phrase that reads the same forwards and backwards.
 *
 * <p>
 * Example 1:
 * Input: input = "babad"
 * Output: "bab"
 * "aba" is also acceptable.
 *
 * <p>
 * Example 2:
 * Input: input = "cbbd"
 * Output: "bb"
 *
 * <p>
 * Constraints:
 * 1. 1 <= s.length <= 1000
 * 2. The input consists only of digits and alphabets (English letters).
 *
 * <p>
 * Additional Conditions:
 * 1. Time complexity: O(n^2) or better.
 * 2. Space complexity: O(n^2) or better.
 */
public class LongestPalindrome {
    private static class PalindromeInclusiveBound {
        private int leftBound;
        private int rightBound;
        public PalindromeInclusiveBound(int leftBound, int rightBound) {
            this.leftBound = leftBound;
            this.rightBound = rightBound;
        }

        public int getLeftBound() {
            return leftBound;
        }

        public int getRightBound() {
            return rightBound;
        }

        public void setBound(int leftBound, int rightBound) {
            this.leftBound = leftBound;
            this.rightBound = rightBound;
        }
    }

    public String findLongestPalindrome(String input) {
        // TODO implementation
        return null;
    }

    private void findLongestPalindrome_test1() {
        String input = "babad";
        String longestPalindrome = findLongestPalindrome(input);

        System.out.println("findLongestPalindrome_test1");
        if (longestPalindrome.equals("bab") || longestPalindrome.equals("aba")) {
            System.out.println("Passed.");
        } else {
            System.out.println("Failed.");
        }
    }

    private void findLongestPalindrome_test2() {
        String input = "cbbd";
        String longestPalindrome = findLongestPalindrome(input);

        System.out.println("findLongestPalindrome_test2");
        if (longestPalindrome.equals("bb")) {
            System.out.println("Passed.");
        } else {
            System.out.println("Failed.");
        }
    }

    public static void main(String[] args) {
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        longestPalindrome.findLongestPalindrome_test1();
        longestPalindrome.findLongestPalindrome_test2();
    }
}
