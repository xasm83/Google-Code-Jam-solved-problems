package leetcode;

import java.util.*;
import java.util.stream.Collectors;

//https://leetcode.com/problems/top-k-frequent-elements/
//O(nlogn)

public class SearchTopKFrequentElementsInArray {
    public static List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            Integer oldCount = counts.putIfAbsent(nums[i], 1);
            if (oldCount != null) {
                counts.put(nums[i], counts.get(nums[i]) + 1);
            }
        }

        NavigableMap<Integer, Set<Integer>> countsTree = new TreeMap<>();
        counts.forEach((arrayValue, countForArrayValue) -> {
            Set<Integer> valuesWithTheSameCount = new HashSet<>();
            valuesWithTheSameCount.add(arrayValue);
            Set<Integer> oldValuesWithTheSameCount = countsTree.putIfAbsent(countForArrayValue, valuesWithTheSameCount);
            if (oldValuesWithTheSameCount != null) {
                oldValuesWithTheSameCount.add(arrayValue);
            }
        });

        return countsTree.descendingMap().values().stream().flatMap(Collection::stream).limit(k).collect(Collectors.toList());
    }
}
