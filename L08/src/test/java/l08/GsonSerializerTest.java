package l08;

import com.google.gson.Gson;
import l08.cases.Arrays;
import l08.cases.Empty;
import l08.cases.Integers;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class GsonSerializerTest {

    Gson gson;
    MyJsonSerializer myJsonSerializer;

    @Before
    public void setUp() {
        gson = new Gson();
        myJsonSerializer = new MyJsonSerializer();
    }

    @Test
    public void empty() {
        String json = myJsonSerializer.toJson(new Empty());
        assertEquals("{}", json);
    }

    @Test
    public void integers() {
        Integers integers = new Integers();
        integers.x = 5;
        integers.y = 10;
        String json = myJsonSerializer.toJson(integers);
        Integers resultIntegers = gson.fromJson(json, Integers.class);
        assertEquals(5, resultIntegers.x);
        assertEquals(10, resultIntegers.y);
    }

    @Test
    public void arrays() {
        Arrays arrays = new Arrays();
        arrays.x = new int[]{1, 2, 3};
        arrays.y = new float[]{1.5f, 1.6f};
        String json = myJsonSerializer.toJson(arrays);
        Arrays resultArrays = gson.fromJson(json, Arrays.class);
        assertArrayEquals(arrays.x, resultArrays.x);
        assertArrayEquals(arrays.y, resultArrays.y, 0.01f);
    }
}