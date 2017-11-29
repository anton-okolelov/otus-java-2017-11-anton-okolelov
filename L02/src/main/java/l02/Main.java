package l02;

@SuppressWarnings({"RedundantStringConstructorCall"})
public class Main {

    public static void main(String[] args) throws InterruptedException {

        int[] testArraySizes = {1000, 10_000, 100_000, 1_000_000, 10_000_000};

        ObjectSizePrinter sizePrinter = new ObjectSizePrinter(testArraySizes);

        sizePrinter.print("Empty string", () -> new String(""));
        sizePrinter.print("Empty string without string pool", () -> new String(new char[0]));
        sizePrinter.print("\"Anton\" string", () -> new String(new char[]{'A', 'n', 't', 'o', 'n'}));
        sizePrinter.print("Integer", () -> new Integer(5));
        sizePrinter.print("Object", () -> new Object());
        sizePrinter.print("Empty array of Integers", () -> new Integer[]{});
        sizePrinter.print("Array of 10 Integers", () -> new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        sizePrinter.print("Class with integer", () -> new ClassWithInteger());

    }
}
