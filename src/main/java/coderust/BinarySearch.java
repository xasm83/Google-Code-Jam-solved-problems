package coderust;

public class BinarySearch {

    public static int binarySearch(int value, int[] array) {
        return binarySearchIterative(value, array);
    }

    public static int binarySearchIterative(int value, int[] array) {
        int startIndex = 0;
        int endIndex = array.length - 1;

        while (startIndex <= endIndex) {
            int splitIndex = startIndex + (endIndex - startIndex) / 2;
            if (array[splitIndex] == value) {
                return splitIndex;
            }
            if (array[splitIndex] < value) {
                startIndex = splitIndex + 1;
            } else {
                endIndex = splitIndex - 1;
            }
        }
        return -1;
    }

    private static int binarySearchRecursive(int value, int[] array, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return -1;
        }

        int splitIndex = startIndex + (endIndex - startIndex) / 2;
        if (array[splitIndex] == value) {
            return splitIndex;
        }

        if (array[splitIndex] < value) {
            return binarySearchRecursive(value, array, splitIndex + 1, endIndex);
        } else {
            return binarySearchRecursive(value, array, startIndex, splitIndex - 1);
        }
    }
}
