package problems.valid_palindrome;

public class Solution {

    public static boolean isPalindrome(String s) {
        var processed = s.toLowerCase()
                        .chars()
                        .filter(a -> Character.isAlphabetic(a) || Character.isDigit(a))
                        .mapToObj(i -> (char) i)
                        .toList();

        for (int i = 0, j = processed.size() - 1; i < j; i++, j--) {
            if (processed.get(i) != processed.get(j)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        isPalindrome("A man, a plan, a canal: Panama");
    }
}
