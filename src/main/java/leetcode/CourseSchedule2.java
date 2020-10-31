package leetcode;

import java.util.*;
import java.util.stream.Collectors;

//https://leetcode.com/problems/course-schedule-ii/submissions/
//need to cut off visited children or check it per node
//reversed post order traversalx
class CourseSchedule2 {
    boolean cycle = false;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> nodes = new HashMap<>();

        for (int[] prerequisite : prerequisites) {
            int node = prerequisite[0];
            int parent = prerequisite[1];
            nodes.computeIfAbsent(parent, k -> new HashSet<>());
            nodes.get(parent).add(node);
        }

        System.out.println(nodes);
        LinkedList<Integer> result = new LinkedList<>();
        boolean[] visited = new boolean[numCourses];


        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                Deque<Integer> stack = new LinkedList<>();
                stack.push(i);
                boolean[] inRecursion = new boolean[numCourses];
                inRecursion[i] = true;

                while (!stack.isEmpty()) {
                    Integer node = stack.peek();
                    inRecursion[node] = true;
                    if (visited[node]) {
                        stack.pop();
                        continue;
                    }

                    if (nodes.get(node) != null) {
                        Set<Integer> cnodes = nodes.get(node).stream().filter(n -> !visited[n]).collect(Collectors.toSet());
                        nodes.put(node, cnodes);
                    }

                    if (nodes.get(node) == null || nodes.get(node).isEmpty()) {
                        result.add(node);
                        visited[node] = true;
                        stack.pop();
                    } else {
                        nodes.get(node).forEach(nn -> {
                            if (inRecursion[nn]) cycle = true;
                            stack.push(nn);
                        });
                    }
                    if (cycle) return new int[0];
                }
            }
        }
        Collections.reverse(result);
        return result.stream().mapToInt(i -> i).toArray();
    }
}


//need to track recursion stack before each dfs call
class CourseSchedule2Recursion {
    boolean cycle;
    Map<Integer, Set<Integer>> nodes = new HashMap<>();
    LinkedList<Integer> result = new LinkedList<>();
    boolean[] visited;
    Set<Integer> recursionStack;

    public int[] findOrder(int numCourses, int[][] prerequisites) {

        for (int[] prerequisite : prerequisites) {
            int node = prerequisite[0];
            int parent = prerequisite[1];
            nodes.computeIfAbsent(parent, k -> new HashSet<>());
            nodes.get(parent).add(node);
        }
        visited = new boolean[numCourses];
        System.out.println(nodes);

        for (int i = 0; i < numCourses; i++) {
            recursionStack = new HashSet<>();
            if (!visited[i] && !cycle) dfs(i);
        }
        Collections.reverse(result);
        return cycle ? new int[0] : result.stream().mapToInt(i -> i).toArray();
    }

    private void dfs(int nodeIndex) {
        if (recursionStack.contains(nodeIndex)) {
            cycle = true;
        }
        if (cycle || visited[nodeIndex]) return;

        recursionStack.add(nodeIndex);
        Set<Integer> children = nodes.get(nodeIndex);
        if (children != null) children.stream().filter(n -> !visited[n]).forEach(this::dfs);
        result.add(nodeIndex);
        visited[nodeIndex] = true;
        recursionStack.remove(nodeIndex);
    }
}