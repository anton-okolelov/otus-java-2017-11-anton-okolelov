package l05.myunit;

import java.util.ArrayList;
import java.util.List;

public class TestResult {

    private List<FailInfo> failedMethodList;
    private List<String> successMethodList;

    TestResult() {
        failedMethodList = new ArrayList<>();
        successMethodList = new ArrayList<>();
    }

    public void addFailedMethod(FailInfo failInfo) {
        failedMethodList.add(failInfo);
    }

    public void addSuccessMethod(String methodName) {
        successMethodList.add(methodName);
    }

    public List<FailInfo> getFailures() {
        return failedMethodList;
    }

    public List<String> getSuccessMethodList() {
        return successMethodList;
    }
}
