package l02;

class SizePrinter {

    private final TestingRig testingRig;

    SizePrinter(int testArraySize) {
        testingRig = new TestingRig(testArraySize);
    }

    void print(String comment, IObjectFactory factory) throws InterruptedException {
        long memory = testingRig.getObjectMemorySize(factory);
        System.out.println(comment + ": " + memory + " bytes");
    }
}
