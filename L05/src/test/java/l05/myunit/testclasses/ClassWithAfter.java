package l05.myunit.testclasses;

import l05.myunit.annotations.After;
import l05.myunit.annotations.Before;
import l05.myunit.annotations.Test;

public class ClassWithAfter {

    public static String value = "";

    @Before
    public void before() {
        value = "";
    }

    @Test
    public void test() {

    }

    @After
    public void after() {
        value = "after";
    }
}
