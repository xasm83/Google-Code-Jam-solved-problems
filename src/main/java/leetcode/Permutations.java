package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Permutations {
    public List<List<Integer>> permute(int[] nums) {
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        return helper(list);
    }

    public List<List<Integer>> helper(List<Integer> list) {
        System.out.println("got list:" + list);
        if (list.size() == 1) {
            return Arrays.asList(new ArrayList<>(list));
        }
        return IntStream.range(0, list.size())
                .boxed()
                .map(index -> {
                    Integer el = list.remove(0);
                    List<List<Integer>> subResult = helper(list);
                    list.add(el);
                    return subResult
                            .stream()
                            .map(subList -> {
                                subList.add(0, el);
                                return subList;
                            }).collect(Collectors.toList());
                }).reduce(new ArrayList<>(), (a, b) -> {
                    a.addAll(b);
                    return a;
                });
    }
}
