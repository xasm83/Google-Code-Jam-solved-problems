import java.util.LinkedList;
import java.util.Queue;

public class QuickSortIterative {
    public static void sort(int[] nums) {
        if (nums.length == 0) {
            return;
        }
        Queue<Integer> starts = new LinkedList<>();
        Queue<Integer> ends = new LinkedList<>();
        starts.add(0);
        ends.add(nums.length - 1);
        while (!starts.isEmpty()) {
            int start = starts.remove();
            int end = ends.remove();
            int pivotIndex = start + (end - start) / 2;
            int currentIndex = start;
            while (currentIndex <= pivotIndex) {
                if (nums[currentIndex] > nums[pivotIndex]) {
                    pivotIndex = switchElementWithPivot(pivotIndex, currentIndex, nums);
                } else {
                    currentIndex++;
                }
            }
            currentIndex = pivotIndex + 1;
            while (currentIndex <= end) {
                if (nums[currentIndex] < nums[pivotIndex]) {
                    pivotIndex = switchElementWithPivot(pivotIndex, currentIndex, nums);
                } else {
                    currentIndex++;
                }
            }
            if (start < pivotIndex - 1) {
                starts.add(start);
                ends.add(pivotIndex - 1);
            }

            if (end > pivotIndex + 1) {
                starts.add(pivotIndex + 1);
                ends.add(end);
            }
        }
    }

    private static int switchElementWithPivot(int pivotIndex, int elementIndex, int[] nums) {
        if (pivotIndex > elementIndex) {
            switchElements(pivotIndex - 1, elementIndex, nums);
            switchElements(pivotIndex - 1, pivotIndex, nums);
            return pivotIndex - 1;
        } else {
            switchElements(pivotIndex + 1, elementIndex, nums);
            switchElements(pivotIndex + 1, pivotIndex, nums);
            return pivotIndex + 1;
        }
    }

    private static void switchElements(int firstIndex, int secondIndex, int[] nums) {
        int temp = nums[secondIndex];
        nums[secondIndex] = nums[firstIndex];
        nums[firstIndex] = temp;
    }
}

