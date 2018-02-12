package l08;

import com.google.gson.Gson;
import l08.cases.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MyJsonSerializerTest {

    Gson gson;
    l08.MyJsonSerializer myJsonSerializer;

    @Before
    public void setUp() {
        gson = new Gson();
        myJsonSerializer = new l08.MyJsonSerializer();
    }

    @Test
    public void empty() {
        String json = myJsonSerializer.toJsonString(new EmptyExample());
        assertEquals("{}", json);
    }

    @Test
    public void integers() {
        IntegersExample integers = new IntegersExample();
        integers.x = 5;
        integers.y = 10;
        String json = myJsonSerializer.toJsonString(integers);
        IntegersExample resultIntegers = gson.fromJson(json, IntegersExample.class);
        assertEquals(5, resultIntegers.x);
        assertEquals(10, resultIntegers.y);
    }

    @Test
    public void arrays() {
        ArraysExample arrays = new ArraysExample();
        arrays.x = new int[]{1, 2, 3};
        arrays.y = new float[]{1.5f, 1.6f};
        String json = myJsonSerializer.toJsonString(arrays);
        ArraysExample resultArrays = gson.fromJson(json, ArraysExample.class);
        assertArrayEquals(arrays.x, resultArrays.x);
        assertArrayEquals(arrays.y, resultArrays.y, 0.01f);
    }

    @Test
    public void stringsArray() {
        ObjectArrayExample example = new ObjectArrayExample();
        example.strings = new String[]{"x", "y", "z"};
        String json = myJsonSerializer.toJsonString(example);
        ObjectArrayExample result = gson.fromJson(json, ObjectArrayExample.class);
        assertArrayEquals(example.strings, result.strings);

    }

    @Test
    public void collections() {
        CollectionsExample collections = new CollectionsExample();
        collections.list = java.util.Arrays.asList("a", "b", "c");
        collections.set = new HashSet<>(java.util.Arrays.asList(1,2,3));
        collections.map = new HashMap<>();
        collections.map.put("x", "y");

        String json = myJsonSerializer.toJsonString(collections);

        CollectionsExample resultCollections = gson.fromJson(json, CollectionsExample.class);
        assertEquals(resultCollections.list.get(0), "a");
        assertTrue(resultCollections.set.contains(2));
        assertEquals(resultCollections.map.get("x"), "y");
    }

    @Test
    public void nestedObjects() {
        NestedObjectsExample nested = new NestedObjectsExample();
        String json = myJsonSerializer.toJsonString(nested);
        System.out.println(json);
        NestedObjectsExample result = gson.fromJson(json, NestedObjectsExample.class);
        assertEquals(result.arrays.x.length, nested.arrays.x.length);
    }
}