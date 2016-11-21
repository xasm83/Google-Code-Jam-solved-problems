public class DetectIfArrayIsRotatedCopy {
    public static boolean detect(int[] original, int[] target) {
        if (original.length != target.length) {
            return false;
        }

        if (original.length == 0) {
            return true;
        }

        int start = 0;
        int end = target.length;
        int anchorItemIndex = findFirst(target, start, end, original[0]);
        System.out.println("Found anchor element index:" + anchorItemIndex);

        //handle duplicate values
        while (anchorItemIndex >= 0) {
            int itemsProcessed = 0;
            int currentIndex = anchorItemIndex;
            while (itemsProcessed < original.length) {
                if (currentIndex == original.length) {
                    currentIndex %= original.length;
                    System.out.println("Reached end of target array");
                }

                if (original[itemsProcessed] == target[currentIndex]) {
                    itemsProcessed++;
                    currentIndex++;
                    System.out.println("itemsProcessed:"+ itemsProcessed + " currentIndex:" + currentIndex);
                    if (itemsProcessed == original.length) {
                        return true;
                    }
                } else {
                    break;
                }
            }
            if (anchorItemIndex != original.length - 1) {
                anchorItemIndex = findFirst(target, anchorItemIndex + 1, end, original[0]);
                System.out.println("Found next anchor element index:" + anchorItemIndex);
            } else {
                break;
            }
        }
        return false;
    }

    private static int findFirst(int[] array, int start, int end, int value) {
        for (int i = start; i < end; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
