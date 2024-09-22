package problem;

/**
 * 주어진 String input 으로부터 가장 긴 palindrome (회문) substring 을 구하세요.
 * 회문: 回文, 앞 에서 부터 읽으나 뒤 에서 부터 읽으나 같은 단어나 어구
 * <p>
 * Example 1:
 * Input: input = "babad"
 * Output: "bab"
 * "aba" 도 가능
 * <p>
 * Example 2:
 * Input: input = "cbbd"
 * Output: "bb"
 * <p>
 * Constraints:
 * 1 <= s.length <= 1000
 * input consist of only digits and alphabets (English letters)
 * <p>
 * 제약 사항:
 * 1. time complexity: O(n^2) 이하
 * 2. space complexity: O(n^2) 이하
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
