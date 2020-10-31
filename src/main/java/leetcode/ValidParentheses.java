package leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
//https://leetcode.com/problems/valid-parentheses/
public class ValidParentheses {
    public boolean isValid(String s) {
        Map<Character, Character> chars = new HashMap() {{
            put('}', '{');
            put(')', '(');
            put(']', '[');
        }};
        Deque<Character> stck = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            Character chr = s.charAt(i);
            if (chars.containsKey(chr)) {
                if (stck.pollFirst() != chars.get(chr)) return false;
            } else {
                stck.push(chr);
            }
        }
        return stck.isEmpty() && true;
    }
}
