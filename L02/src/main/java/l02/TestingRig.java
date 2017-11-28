package l02;

class TestingRig {

    private int testArraySize;

    TestingRig(int testArraySize) {
        this.testArraySize = testArraySize;
    }

    long getObjectMemorySize(IObjectFactory factory) throws InterruptedException {
        System.gc();


        Thread.sleep(10);

        Object[] testArray = new Object[testArraySize];

        long memoryBeforeMeasure = getUsedMemorySize();

        for (int i = 0; i < testArraySize; i++) {
            testArray[i] = factory.createObject();
        }

        long memoryAfterMeasure = getUsedMemorySize();
        return (memoryAfterMeasure - memoryBeforeMeasure) / testArraySize;
    }

    private long getUsedMemorySize() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
}
