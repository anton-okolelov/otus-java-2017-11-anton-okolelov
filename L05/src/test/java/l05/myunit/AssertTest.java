package l05.myunit;

import org.junit.Test;

import static org.junit.Assert.fail;

public class AssertTest {

    @Test
    public void assertTrue() {
        try {
            Assert.assertTrue(true);
        } catch (AssertException e) {
            fail();
        }
    }

    @Test(expected = AssertException.class)
    public void assertTrueFail() {
        Assert.assertTrue(false);
    }

    @Test
    public void assertEquals() {

        try {
            Assert.assertEquals("A", "A");
        } catch (AssertException e) {
            fail();
        }
    }

    @Test(expected = AssertException.class)
    public void assertEqualsFail() {
        Assert.assertEquals("A", "B");
    }


    @Test(expected = AssertException.class)
    public void failTest() {
        Assert.fail();
    }
}
