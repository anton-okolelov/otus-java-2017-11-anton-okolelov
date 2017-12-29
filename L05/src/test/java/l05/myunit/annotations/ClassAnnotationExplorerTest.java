package l05.myunit.annotations;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import l05.myunit.testclasses.TestTest;
import org.junit.Before;
import org.junit.Test;

public class ClassAnnotationExplorerTest {

    private ClassAnnotationExplorer explorer;

    @Before
    public void before() {
        explorer = new ClassAnnotationExplorer(TestTest.class);
    }

    @Test
    public void getAnnotatedMethods() {
        Method[] methods = explorer.getAnnotatedMethods(l05.myunit.annotations.Test.class);
        assertTrue(methods.length > 0);
        assertEquals("annotatedMethod", methods[0].getName());
    }

    @Test
    public void getFirstAnnotatedMethod() {
        Method method = explorer.getFirstAnnotatedMethod(l05.myunit.annotations.Test.class);
        assertEquals("annotatedMethod", method.getName());
    }

    @Test
    public void getInstance() {
        assertEquals(TestTest.class, explorer.getInstance().getClass());
    }
}
