package l05.myunit.testclasses;

import l05.myunit.annotations.Before;
import l05.myunit.annotations.Test;

import static l05.myunit.Assert.assertEquals;

public class ClassWithBefore {
    private int init;

    @Before
    public void before() {
        init = 5;
    }

    @Test
    public void test() {
        assertEquals(5, init);
    }
}
