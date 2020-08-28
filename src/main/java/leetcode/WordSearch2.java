package leetcode;

import java.util.*;

//https://leetcode.com/problems/word-search-ii/submissions/
//https://leetcode.com/problems/word-search-ii/discuss/797134/Trie-DFS-Backtracking-or-Steps-and-Well-commented
//DFS+trie+visited map, recursion less, you could use dfs(starti,startj) with recursion
//while marking visited characters as '*'(dfs you wont go up) in array and removing words from trie once you hit them
class WordSearch2 {
    public List<String> findWords(char[][] board, String[] words) {

        Set<String> wordSet = new HashSet<>();
        Trie stringTrie = new Trie();
        Arrays.stream(words).forEach(word -> {
            stringTrie.addString(word);
            wordSet.add(word);
        });
        Set<String> result = new HashSet<>();


        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                Deque<Point> queue = new LinkedList<>();
                boolean[][] initVisited = new boolean[board.length][board[0].length];
                initVisited[i][j] = true;
                Point p = new Point();
                p.i = i;
                p.j = j;
                p.prefix = "" + board[i][j];
                p.visited = initVisited;
                queue.push(p);

                if (wordSet.contains(p.prefix)) {
                    result.add(p.prefix);
                }

                while (!queue.isEmpty()) {
                    Point currentPoint = queue.pop();
                    boolean[][] visited = currentPoint.visited;

                    if (currentPoint.i - 1 >= 0 &&
                            stringTrie.hasString(currentPoint.prefix + board[currentPoint.i - 1][currentPoint.j]) &&
                            !visited[currentPoint.i - 1][currentPoint.j]) {
                        String pprefix = currentPoint.prefix + board[currentPoint.i - 1][currentPoint.j];
                        Point newPoint = new Point();
                        newPoint.prefix = pprefix;
                        newPoint.i = currentPoint.i - 1;
                        newPoint.j = currentPoint.j;
                        boolean[][] newVisited = arrayCopy(visited, new boolean[board.length][board[0].length]);
                        newVisited[currentPoint.i - 1][currentPoint.j] = true;
                        newPoint.visited = newVisited;
                        queue.push(newPoint);
                        if (wordSet.contains(pprefix)) {
                            result.add(pprefix);
                        }
                    }

                    if (currentPoint.i + 1 < board.length &&
                            stringTrie.hasString(currentPoint.prefix + board[currentPoint.i + 1][currentPoint.j]) &&
                            !visited[currentPoint.i + 1][currentPoint.j]) {
                        String pprefix = currentPoint.prefix + board[currentPoint.i + 1][currentPoint.j];
                        Point ppp = new Point();
                        ppp.prefix = pprefix;
                        ppp.i = currentPoint.i + 1;
                        ppp.j = currentPoint.j;
                        boolean[][] newVisited = arrayCopy(visited, new boolean[board.length][board[0].length]);
                        newVisited[currentPoint.i + 1][currentPoint.j] = true;
                        ppp.visited = newVisited;
                        queue.push(ppp);
                        if (wordSet.contains(pprefix)) {
                            result.add(pprefix);
                        }
                    }

                    if (currentPoint.j - 1 >= 0 &&
                            stringTrie.hasString(currentPoint.prefix + board[currentPoint.i][currentPoint.j - 1]) &&
                            !visited[currentPoint.i][currentPoint.j - 1]) {
                        String pprefix = currentPoint.prefix + board[currentPoint.i][currentPoint.j - 1];
                        Point ppp = new Point();
                        ppp.prefix = pprefix;
                        ppp.i = currentPoint.i;
                        ppp.j = currentPoint.j - 1;
                        boolean[][] newVisited = arrayCopy(visited, new boolean[board.length][board[0].length]);
                        newVisited[currentPoint.i][currentPoint.j - 1] = true;
                        ppp.visited = newVisited;
                        queue.push(ppp);
                        if (wordSet.contains(pprefix)) {
                            result.add(pprefix);
                        }
                    }

                    if (currentPoint.j + 1 < board[0].length &&
                            stringTrie.hasString(currentPoint.prefix + board[currentPoint.i][currentPoint.j + 1]) &&
                            !visited[currentPoint.i][currentPoint.j + 1]) {
                        String pprefix = currentPoint.prefix + board[currentPoint.i][currentPoint.j + 1];
                        Point ppp = new Point();
                        ppp.prefix = pprefix;
                        ppp.i = currentPoint.i;
                        ppp.j = currentPoint.j + 1;
                        boolean[][] newVisited = arrayCopy(visited, new boolean[board.length][board[0].length]);
                        newVisited[currentPoint.i][currentPoint.j + 1] = true;
                        ppp.visited = newVisited;
                        queue.push(ppp);
                        if (wordSet.contains(pprefix)) {
                            result.add(pprefix);
                        }
                    }
                }
            }
        }
        return new ArrayList(result);
    }

    public static boolean[][] arrayCopy(boolean[][] aSource, boolean[][] aDestination) {
        for (int i = 0; i < aSource.length; i++) {
            System.arraycopy(aSource[i], 0, aDestination[i], 0, aSource[i].length);
        }
        return aDestination;
    }


    class Point {
        int i;
        int j;
        String prefix;
        boolean[][] visited;

        public String toString() {
            return "i: " + i + " j: " + j + " p: " + prefix;
        }
    }

    class Node {
        Map<Character, Node> next = new HashMap<Character, Node>();
    }

    class Trie {
        Node root = new Node();

        boolean hasString(String string) {
            boolean result = false;
            Node node = root;

            for (int i = 0; i < string.length(); i++) {
                char chr = string.charAt(i);
                if (node.next.containsKey(chr)) {
                    node = node.next.get(chr);
                    if (i == string.length() - 1) {
                        return true;
                    }
                } else {
                    break;
                }
            }
            return result;
        }

        void addString(String string) {
            System.out.println("adding: " + string);
            Node node = root;
            for (int i = 0; i < string.length(); i++) {
                char chr = string.charAt(i);
                if (node.next.containsKey(chr)) {
                    node = node.next.get(chr);
                } else {
                    Node newNode = new Node();
                    node.next.put(chr, newNode);
                    node = newNode;
                }
            }
        }
    }
}
