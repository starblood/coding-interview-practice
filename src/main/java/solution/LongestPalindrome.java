package solution;

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
        int n = input.length();
        // set all values of memory n * n elements are false
        boolean [][] memory = new boolean[n][n];
        PalindromeInclusiveBound bound = new PalindromeInclusiveBound(0, 0);

        // if length of substring is 1, this should be always palindrome.
        for (int i = 0; i < n; i++) {
            // memory[0][0] = true, memory[1][1] = true, ... memory[n-1][n-1] = true
            memory[i][i] = true;
        }

        for (int i = 0; i < n - 1; i++) {
            /* A substring of length 2 is a palindrome if both characters are equal.
                That is, i, i + 1 is a palindrome if input[i] == input[i + 1] */
            if (input.charAt(i) == input.charAt(i + 1)) {
                memory[i][i + 1] = true;
                bound.setBound(i, i + 1);
            }
        }

        /* We know that all substrings of length 1 are palindromes.
           From this, we can check if each substring of length 3 is a palindrome using the above fact.
           We just need to check every i, j pair where j - i = 2.
           Once we know all palindromes of length 3, we can use that information to find all palindromes of length 5,
           and then 7, and so on.

           What about even-length palindromes? A substring of length 2 is a palindrome if both characters are equal.
           That is, i, i + 1 is a palindrome if s[i] == s[i + 1].
           From this, we can use the earlier logic to find all palindromes of length 4, then 6, and so on.

           We need to populate the table.
           We iterate over all i, j pairs, starting with pairs that have a difference of 2
           (representing substrings of length 3), then pairs with a difference of 3, then 4, and so on. */
        for (int diff = 2; diff < n; diff++) {
            for (int i = 0; i < n - diff; i++) {
                int j = i + diff;
                // we found a palindrome
                if (input.charAt(i) == input.charAt(j) && memory[i + 1][j - 1]) {
                    memory[i][j] = true;
                    bound.setBound(i, j);
                }
            }
        }

        int i = bound.getLeftBound();
        int j = bound.getRightBound();
        return input.substring(i, j + 1); // substring 2nd operand is exclusive.
    }

    /* more optimal than 'findLongestPalindrome()' (DP version)
       time complexity: O(n^2)
       space complexity: O(1)
     */
    public String findLongestPalindrome2(String s) {
        int[] ans = new int[] { 0, 0 };

        for (int i = 0; i < s.length(); i++) {
            int oddLength = expand(i, i, s);
            if (oddLength > ans[1] - ans[0] + 1) {
                int dist = oddLength / 2;
                ans[0] = i - dist;
                ans[1] = i + dist;
            }

            int evenLength = expand(i, i + 1, s);
            if (evenLength > ans[1] - ans[0] + 1) {
                int dist = (evenLength / 2) - 1;
                ans[0] = i - dist;
                ans[1] = i + 1 + dist;
            }
        }

        int i = ans[0];
        int j = ans[1];
        return s.substring(i, j + 1);
    }

    private int expand(int i, int j, String s) {
        int left = i;
        int right = j;

        while (
                left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)
        ) {
            left--;
            right++;
        }

        return right - left - 1;
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
