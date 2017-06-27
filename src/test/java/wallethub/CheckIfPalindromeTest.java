package wallethub;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckIfPalindromeTest {

    @Test
    public void whenBaseCaseSymmetricShouldReturnTrue() {
        assertTrue(CheckIfPalindrome.isPalindrom("abba"));
    }

    @Test
    public void whenBaseCaseAsymmetricShouldReturnTrue() {
        assertTrue(CheckIfPalindrome.isPalindrom("abcba"));
    }

    @Test
    public void whenPalindromeSingleLetterShouldReturnTrue() {
        assertTrue(CheckIfPalindrome.isPalindrom("a"));
    }

    @Test
    public void whenPalindromeSameLetterShouldReturnTrue() {
        assertTrue(CheckIfPalindrome.isPalindrom("aaa"));
    }

    @Test
    public void whenPalindromeEmptyStringShouldReturnTrue() {
        assertTrue(CheckIfPalindrome.isPalindrom(""));
    }

    @Test
    public void whenNonPalindromeShouldReturnFalse() {
        assertFalse(CheckIfPalindrome.isPalindrom("ac"));
    }

    @Test
    public void whenNonPalindromeLongShouldReturnFalse() {
        assertFalse(CheckIfPalindrome.isPalindrom("abc"));
    }

    @Test
    public void testPseudoPalindrome() {
        assertFalse(CheckIfPalindrome.isPalindrom("abca"));
    }
}
