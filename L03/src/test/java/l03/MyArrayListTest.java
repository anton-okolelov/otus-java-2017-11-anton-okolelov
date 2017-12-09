package l03;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class MyArrayListTest {
    @Test
    public void addAll() {

        MyArrayList<Integer> myList = new MyArrayList<>();

        Integer int1 = 1;
        Integer int2 = 2;
        Collections.addAll(myList, int1, int2);
        assertTrue(myList.contains(int1) && myList.contains(int2));
    }

    @Test
    public void capacityChange() {
        ArrayList<Integer> list = new ArrayList<>();
        MyArrayList<Integer> myList = new MyArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Integer element = i;
            list.add(element);
            myList.add(element);
        }

        for (Integer element : list) {
            assertTrue(myList.contains(element));
        }
    }

    @Test
    public void get() {
        String x = "a";
        MyArrayList<String> list = new MyArrayList<>();
        list.add(x);
        assertSame(list.get(0), x);
    }

    @Test
    public void set() {
        MyArrayList<String> list = new MyArrayList<>();
        list.set(0, "a");
        assertEquals(list.get(0), "a");
    }

    @Test
    public void copy() {
        Integer int1 = 1;
        Integer int2 = 2;
        MyArrayList<Integer> list1 = new MyArrayList<>();
        list1.add(int1);
        list1.add(int2);

        MyArrayList<Integer> list2 = new MyArrayList<>();
        Collections.addAll(list2, 3, 3, 3);
        Collections.copy(list2, list1);
        assertTrue(list2.contains(int2));
    }

    @Test
    public void copy11Elements() {
        MyArrayList<Integer> source = new MyArrayList<>();
        MyArrayList<Integer> destination = new MyArrayList<>();

        IntStream.range(1, 11).forEach(item -> {
            source.add(item);
            destination.add(0);
        });

        Collections.copy(destination, source);
        source.forEach((item) -> {
            assertTrue(destination.contains(item));
        });

    }

    @Test
    public void contains() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        assertTrue(list.contains(1));
        assertFalse(list.contains(2));
    }

    @Test
    public void listIterator() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        list.add(2);
        ListIterator<Integer> iterator = list.listIterator();
        assertTrue(iterator.hasNext());
        assertFalse(iterator.hasPrevious());
        assertEquals(new Integer(1), iterator.next());
        assertEquals(new Integer(2), iterator.next());
    }

    @Test
    public void sort() {
        MyArrayList<Integer> list = new MyArrayList<>();
        Collections.addAll(list, 3, 1, 2);
        Collections.sort(list, Comparator.naturalOrder());
        assertArrayEquals(new Integer[]{1, 2, 3}, list.toArray());
    }

    @Test
    public void removeItem() {
        Integer int0 = 1;
        Integer int1 = 2;
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(0, int0);
        list.add(1, int1);
        list.remove(1);
        assertEquals(1, list.size());
        assertTrue(list.contains(int0));
        assertFalse(list.contains(int1));
    }
}