package l05;

import l05.myunit.annotations.After;
import l05.myunit.annotations.Before;
import l05.myunit.annotations.Test;
import static l05.myunit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class ExampleTestClass {
    private List<String> list = new ArrayList<>();

    @Before
    public void before() {
        list.add("A");
        list.add("B");
    }

    @Test
    public void testFail() {
        // сфейлится
        assertEquals(1, list.size());
    }

    @Test
    public void testAnotherFail() {
        // сфейлится
        fail();
    }


    @Test
    public void testOk() {
        assertTrue(!list.isEmpty());
    }


    @After
    public void after() {
        list.clear();
    }
}
