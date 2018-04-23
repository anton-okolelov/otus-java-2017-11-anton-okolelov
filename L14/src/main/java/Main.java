import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[] array = new int[1_000_000];
        Arrays.setAll(array, i -> (int) (Math.random() * 10000));
        int[] result = new MultiThreadSorter(4).sort(array);
        System.out.println(result[0]);
        System.out.println(result[5000]);
        System.out.println(result[999999]);
    }
}
