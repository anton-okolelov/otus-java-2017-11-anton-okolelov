package l05.myunit;

import l05.myunit.annotations.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyUnit {

    public MyUnit() {

    }

    public TestResult testClass(Class<?> klass) {
        TestResult testResult = new TestResult();
        ClassAnnotationExplorer classExplorer = new ClassAnnotationExplorer(klass);

        Method before = classExplorer.getFirstAnnotatedMethod(Before.class);
        Method after = classExplorer.getFirstAnnotatedMethod(After.class);
        Method[] tests = classExplorer.getAnnotatedMethods(Test.class);

        for (Method test : tests) {
            Object instance = classExplorer.getInstance();

            try {
                if (before != null) {
                    before.invoke(instance);
                }

                try {
                    test.invoke(instance);
                } catch (InvocationTargetException e) {
                    testResult.addFailedMethod(new FailInfo(test.getName(), e.getTargetException().getMessage()));
                }
                testResult.addSuccessMethod(test.getName());

                if (after != null) {
                    after.invoke(instance);
                }
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException();
            }
        }
        return testResult;
    }

    public Map<Class<?>, TestResult> testPackage(String packageName) {
        Map<Class<?>, TestResult> resultMap = new HashMap<>();
        try {

            List<Class<?>> classes = new PackageClassFinder().getClasses(packageName);

            for (Class<?> klass: classes) {
                if (new ClassAnnotationExplorer(klass).hasAnnotatedMethod(Test.class)) {
                    resultMap.put(klass, testClass(klass));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return resultMap;
    }
}
