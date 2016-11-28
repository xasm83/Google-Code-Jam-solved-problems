package coderust;


import java.util.Arrays;

public class ArrayMaximumSlidingWindow {

    public static void main(String[] args) {
        calculate(new int[]{3, 4, 10, 5, 1, 7, 6, 3, 8, 0,}, 4);
    }

    public static void calculate(int[] array, int windowSize) {
        FifoQueue fifoQueue = new FifoQueue(windowSize);
        int slidingWindowSum = 0;

        for (int item : array) {
            if (fifoQueue.getSize() == windowSize) {
                System.out.println("Queue content before removal: " + fifoQueue);
                int oldestItem = fifoQueue.remove();
                slidingWindowSum -= oldestItem;
                System.out.println("Removing " + oldestItem);
                System.out.println("Queue size: " + fifoQueue.getSize());
            }

            fifoQueue.add(item);
            slidingWindowSum += item;
            System.out.println("Queue content " + fifoQueue);
            System.out.println("Sliding sum :" + slidingWindowSum);
            System.out.println("Queue size: " + fifoQueue.getSize());
            System.out.println("------------------------\n");

        }
    }

    private static class FifoQueue {
        int[] circleBufferArray;
        int tailPointer = 0;
        int headPointer = 0;
        int length;
        int size;

        FifoQueue(int queueSize) {
            circleBufferArray = new int[queueSize];
            length = circleBufferArray.length;
        }

        void add(int item) {
            if (size == circleBufferArray.length) {
                throw new RuntimeException("Queue is full.");
            }
            circleBufferArray[headPointer] = item;
            headPointer = (headPointer + 1) % length;
            size++;
        }

        int remove() {
            if (size == 0) {
                throw new RuntimeException("Queue is empty.");
            }
            int value = circleBufferArray[tailPointer];
            tailPointer = (tailPointer + 1) % length;
            size--;
            return value;
        }

        int getSize() {
            return size;
        }

        @Override
        public String toString() {
            return "FifoQueue{" +
                    "circleBufferArray=" + Arrays.toString(circleBufferArray) +
                    '}';
        }
    }
}
