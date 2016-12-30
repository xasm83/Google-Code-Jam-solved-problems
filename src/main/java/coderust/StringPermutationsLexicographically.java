package coderust;

//dups like  "aaaa" are not handled, should be handled with min right char solution
//simple swap will not provide correct order
//iterative solution with incorrect order https://miafish.wordpress.com/2014/12/22/find-all-permutations-of-a-given-string/
public class StringPermutationsLexicographically {
    private void getPermutations(String s, int i) {
        if (i == s.length()) {
            System.out.println(s);
        }
        for (int j = i; j < s.length(); j++) {
            String partiallyPermuted = swap(s, j, i);
            getPermutations(partiallyPermuted, i + 1);
        }
    }

    private String swap(String s, int from, int to) {
        StringBuilder sb = new StringBuilder(s);
        sb.insert(to, s.charAt(from));
        sb.delete(from + 1, from + 2);
        return sb.toString();
    }
}
