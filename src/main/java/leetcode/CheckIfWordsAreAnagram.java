package leetcode;

import java.util.HashMap;
import java.util.Map;
//https://leetcode.com/problems/valid-anagram/
public class CheckIfWordsAreAnagram {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        if (s.length() == 0) {
            return true;
        }
        HashMap<Character, Integer> lettersFirst = new HashMap<>();
        HashMap<Character, Integer> lettersSecond = new HashMap<>();
        countLetters(lettersFirst, s);
        countLetters(lettersSecond, t);
        boolean isAnagram = true;
        for (Map.Entry entry : lettersFirst.entrySet()) {
            if (!entry.getValue().equals(lettersSecond.get(entry.getKey()))) {
                isAnagram = false;
            }
        }
        return isAnagram;
    }

    private void countLetters(Map<Character, Integer> result, String s) {
        for (int i = 0; i < s.length(); i++) {
            Character letter = s.charAt(i);
            Integer oldCount = result.putIfAbsent(letter, 1);
            if (oldCount != null) {
                oldCount++;
                result.put(letter, oldCount);
            }
        }
    }
}
