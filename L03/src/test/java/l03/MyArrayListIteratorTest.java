package l03;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyArrayListIteratorTest {

    @Test
    public void add() {
        Object[] objects = {1, 2, 3};

        MyArrayListIterator<Integer> iterator = new MyArrayListIterator(objects, objects.length);
        iterator.next();
        iterator.set(5);
        assertEquals(new Integer(5), objects[0]);
    }
}