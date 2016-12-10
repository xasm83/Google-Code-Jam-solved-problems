package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

//https://leetcode.com/problems/merge-intervals/
public class MergeOverlappingIntervals {

    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    public class Solution {
        public List<Interval> merge(List<Interval> intervals) {
            if (intervals.isEmpty()) {
                return Collections.emptyList();
            }
            Collections.sort(
                    intervals, (item1, item2) -> {
                        return Integer.compare(item1.start, item2.start);
                    }
            );
            List<Interval> mergedIntervals = new ArrayList<>();
            Iterator<Interval> iterator = intervals.iterator();
            Interval firstInterval = iterator.next();
            int currentStart = firstInterval.start;
            int currentEnd = firstInterval.end;

            while (iterator.hasNext()) {
                Interval interval = iterator.next();
                if (interval.start >= currentStart && interval.start <= currentEnd && interval.end > currentEnd) {
                    currentEnd = interval.end;
                } else if (interval.start > currentEnd) {
                    mergedIntervals.add(new Interval(currentStart, currentEnd));
                    currentStart = interval.start;
                    currentEnd = interval.end;
                }
            }
            mergedIntervals.add(new Interval(currentStart, currentEnd));
            return mergedIntervals;
        }
    }
}
