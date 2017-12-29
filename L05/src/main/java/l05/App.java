package l05;

import l05.myunit.MyUnit;
import l05.myunit.TestResult;

import java.util.Map;

public class App {
    public static void main(String[] args) {
        Map<Class<?>, TestResult> results = new MyUnit().testPackage("l05");

        results.forEach((klass, testResult)->{

            System.out.println(klass.getName() + ":");

            System.out.println("Failed\n-----------");

            testResult.getFailures().forEach(
                    failure -> System.out.println(failure.getMethod() + ": " + failure.getMessage())
            );

            System.out.println("\n\nOkay\n---------------");

            testResult.getSuccessMethodList().forEach(System.out::println);

        });

    }
}
