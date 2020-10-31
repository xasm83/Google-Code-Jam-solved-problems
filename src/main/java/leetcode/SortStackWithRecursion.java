package leetcode;

import java.util.Stack;
//https://practice.geeksforgeeks.org/problems/sort-a-stack/1
public class SortStackWithRecursion {

        public Stack<Integer> sort(Stack<Integer> s)
        {
            if (s.isEmpty()){
                return s;
            }
            Integer x = s.pop();
            sort(s);
            return 	insertAt(x,s);
        }

        public Stack<Integer> insertAt(Integer x, Stack<Integer> s)
        {
            if (s.isEmpty() || x>s.peek()){
                s.push(x);
                return s;
            } else {
                Integer xx = s.pop();
                insertAt(x, s);
                s.push(xx);
                return s;
            }
        }
    }
}
