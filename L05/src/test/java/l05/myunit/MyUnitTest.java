package l05.myunit;

import l05.myunit.testclasses.ClassWithAfter;
import l05.myunit.testclasses.ClassWithBefore;
import l05.myunit.testclasses.ClassWithTestFail;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class MyUnitTest {

    private MyUnit myUnit = new MyUnit();

    @Test
    public void testClassWithFail() {
        TestResult result = myUnit.testClass(ClassWithTestFail.class);
        assertFalse(result.getFailures().isEmpty());
        assertEquals("Fail", result.getFailures().get(0).getMessage());
    }

    @Test
    public void testClassWithBefore() {
        TestResult result = myUnit.testClass(ClassWithBefore.class);
        assertTrue(result.getFailures().isEmpty());
    }

    @Test
    public void testClassWithAfter() {
        TestResult result = myUnit.testClass(ClassWithAfter.class);
        assertEquals("after", ClassWithAfter.value);
    }

    @Test
    public void testPackage() {
        Map<Class<?>, TestResult> resultMap = myUnit.testPackage("l05.myunit.testclasses");
        assertFalse(resultMap.isEmpty());
        assertTrue(resultMap.containsKey(ClassWithAfter.class));

    }

}
