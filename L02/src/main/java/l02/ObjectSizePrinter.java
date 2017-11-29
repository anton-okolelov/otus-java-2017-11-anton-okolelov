package l02;

import java.util.function.Supplier;

class ObjectSizePrinter {

    private final int[] testArraySizes;
    private Object[] testArray;


    ObjectSizePrinter(int[] testArraySizes) {
        this.testArraySizes = testArraySizes;
    }

    void print(String comment, Supplier<Object> objectSupplier) throws InterruptedException {

        System.out.println(comment + ":");

        for (int testArraySize : testArraySizes) {
            long memory = getObjectMemorySize(objectSupplier, testArraySize);
            System.out.println(testArraySize + " elements: " + memory + " bytes");
        }
    }


    private long getObjectMemorySize(Supplier<Object> objectSupplier, int testArraySize) throws InterruptedException {

        testArray = new Object[testArraySize];

        long memoryBeforeMeasure = getUsedMemorySize();

        for (int i = 0; i < testArraySize; i++) {
            testArray[i] = objectSupplier.get();
        }

        long memoryAfterMeasure = getUsedMemorySize();

        return (memoryAfterMeasure - memoryBeforeMeasure) / testArraySize;
    }


    private long getUsedMemorySize() throws InterruptedException {
        runGc();
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }


    private void runGc() throws InterruptedException {
        System.gc();

        // чтобы gc успел сработать
        Thread.sleep(100);
    }
}
