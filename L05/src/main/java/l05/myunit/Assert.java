package l05.myunit;

public class Assert {

    static public void assertTrue(boolean value) {
        if (!value) {
            fail("Value is not true");
        }
    }

    static public void assertEquals(Object expected, Object actual)  {
        if (!expected.equals(actual)) {
            fail("Objects are not equal");
        }
    }

    static public void fail() {
        fail("Fail");
    }

    static private void fail(String message) {
        throw new AssertException(message);
    }
}
