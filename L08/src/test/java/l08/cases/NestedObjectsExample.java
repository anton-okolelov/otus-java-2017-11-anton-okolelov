package l08.cases;

public class NestedObjectsExample {
    public ArraysExample arrays = new ArraysExample();
    private int x = 5;

    public NestedObjectsExample() {
        arrays.x = new int[]{10, 20};
        arrays.y = new float[]{20, 30};
    }
}
