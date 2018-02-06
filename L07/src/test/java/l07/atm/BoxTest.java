package l07.atm;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoxTest {

    @Test
    public void canPut() throws BoxOverflowException {
        Box box = new Box(100, 100);
        box.put(99);
        assertTrue(box.canPut(1));
        assertFalse(box.canPut(2));
    }

    @Test
    public void put() throws BoxOverflowException {
        Box box = new Box(10, 10);
        box.put(1);
        assertEquals(1, box.getBanknotesCount());
    }

    @Test(expected = BoxOverflowException.class)
    public void boxOverflow() throws BoxOverflowException {
        Box box = new Box(100, 100);
        box.put(99);
        box.put(2);
    }
}