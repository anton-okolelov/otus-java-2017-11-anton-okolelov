import org.junit.Test;

import static org.junit.Assert.*;

public class MergeSortedArraysTest {

    @Test
    public void sortTest9Elem() throws InterruptedException {
        MultiThreadSorter sorter = new MultiThreadSorter(4);
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, sorter.sort(arr));
    }

    @Test
    public void sortTest() throws InterruptedException {
        MultiThreadSorter sorter = new MultiThreadSorter(4);
        int[] arr = new int[]{1, 5, 0, 10, 3, 6};
        assertArrayEquals(new int[]{0, 1, 3, 5, 6, 10}, sorter.sort(arr));
    }

    @Test
    public void mergeSortedArrays() {
        int[] arr1 = new int[]{1, 2, 4, 6};
        int[] arr2 = new int[]{1, 3, 5, 7};
        assertArrayEquals(new int[]{1, 1, 2, 3, 4, 5, 6, 7}, MultiThreadSorter.mergeSortedArrays(arr1, arr2));
    }
}
