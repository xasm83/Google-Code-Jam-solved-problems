package codility;

public class BracketsMedianInWord {
    public int solutionB(String S) {
        int closingCount = 0;
        for (int i = S.length() - 1; i >= 0; i--) {
            if (S.charAt(i) == ')') {
                closingCount++;
            }
        }

        int openCount = 0;
        for (int i = 0; i < S.length() - 1; i++) {
            if (openCount == closingCount) {
                return i;
            }

            if (S.charAt(i) == ')') {
                closingCount--;
            } else {
                openCount++;
            }
        }
        return S.length();
    }
}
