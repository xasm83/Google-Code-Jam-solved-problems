package leetcode;

public class FirstUniqueCharacterInString {
    //https://leetcode.com/problems/first-unique-character-in-a-string/
//    use char as array index
    public int firstUniqChar(String s) {
        if (s.length() == 0) return -1;
        if (s.length() == 1) return 0;

        int[] a = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            a[c - 'a'] += 1;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (a[c - 'a'] == 1) return i;
        }
        return -1;
    }
}

