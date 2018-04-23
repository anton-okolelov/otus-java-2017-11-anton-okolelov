import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

public class MultiThreadSorter {

    private final int threadsCount;
    private int[] result;
    private int resultLength = 0;

    public MultiThreadSorter(int threadsCount) {
        this.threadsCount = threadsCount;
    }


    private synchronized void addResultPart(int[] chunk) {
        int[] merged;
        if (resultLength == 0) {
            merged = chunk;
        } else {
            merged = mergeSortedArrays(chunk, Arrays.copyOfRange(result,0 , resultLength ));
        }

        System.arraycopy(merged, 0, result, 0, merged.length);
        resultLength = merged.length;
    }

    public int[] sort(int[] array) throws InterruptedException {
        result = new int[array.length];
        int length = array.length;
        int chunkSize = length / threadsCount;
        if (chunkSize == 0) {
            chunkSize = 1;
        }

        int i = 0;
        List<Thread> threads = new ArrayList<>();
        while (i < length) {
            Thread thread = sortThread(Arrays.copyOfRange(array, i, Math.min(i + chunkSize, array.length)));
            thread.start();
            threads.add(thread);
            i += chunkSize;
        }

        for (Thread t : threads) {
            t.join();
        }

        return result;
    }

    private Thread sortThread(int[] array) {
        return new Thread(() -> {
            Arrays.sort(array);
            addResultPart(array);
        });
    }


    public static int[] mergeSortedArrays(int[] array1, int[] array2) {
        int[] result = new int[array1.length + array2.length];
        int pos1 = 0;
        int pos2 = 0;
        for (int i = 0; i < result.length; i++) {
            if (pos2 >= array2.length) {
                result[i] = array1[pos1];
                pos1++;
            } else if (pos1 >= array1.length) {
                result[i] = array2[pos2];
                pos2++;
            } else if (array1[pos1] <= array2[pos2]) {
                result[i] = array1[pos1];
                pos1++;
            } else {
                result[i] = array2[pos2];
                pos2++;
            }
        }
        return result;
    }

}
