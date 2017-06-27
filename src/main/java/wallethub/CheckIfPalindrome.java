package wallethub;

// O(n) complexity, O(1) space complexity
//there is a alternative implementation with StringBuilder.reverse() - O(n) space complexity
//there is 5 liner solution using recursion and  str.substring()
//this won't work for surrogate unicode characters - specific character parser should be used in that case
public class CheckIfPalindrome {
    public static boolean isPalindrom(String word) {
        int indexLow = 0;
        int indexHigh = word.length() - 1;
        while (indexHigh > indexLow) {
            if (word.charAt(indexLow) != word.charAt(indexHigh)) {
                return false;
            }
            indexLow++;
            indexHigh--;
        }
        return true;
    }
}
