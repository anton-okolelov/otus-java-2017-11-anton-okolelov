package l11;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyCacheEngineTest {
    private MyCacheEngine myCacheEngine;

    @Before
    public void setUp() {
        myCacheEngine = new MyCacheEngine();
    }

    @Test
    public void putAndGet() {
        myCacheEngine.put(1, "test");
        String result = (String) myCacheEngine.get(1);
        assertEquals("test", result);
    }

}
