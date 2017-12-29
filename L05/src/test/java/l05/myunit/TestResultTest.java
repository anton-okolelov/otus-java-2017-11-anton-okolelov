package l05.myunit;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestResultTest {

    @Test
    public void addFailure() {
        TestResult testResult = new TestResult();
        testResult.addFailedMethod(new FailInfo("method", "message"));
        List<FailInfo> failures = testResult.getFailures();
        assertEquals("method", failures.get(0).getMethod());
        assertEquals("message", failures.get(0).getMessage());
    }

}
