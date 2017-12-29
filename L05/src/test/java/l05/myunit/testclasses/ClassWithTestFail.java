package l05.myunit.testclasses;

import l05.myunit.annotations.Test;

import static l05.myunit.Assert.fail;

public class ClassWithTestFail {
    @Test
    public void test() {
        fail();
    }
}
