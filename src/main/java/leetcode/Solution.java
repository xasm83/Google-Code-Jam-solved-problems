package leetcode;

import java.util.*;

class Solution {
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
        System.out.println("nodeIndex " + nodeIndex);
        System.out.println("recursionStack " + recursionStack);
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

        System.out.println("added res " + nodeIndex);
        System.out.println("cycle " + cycle);
    }
}