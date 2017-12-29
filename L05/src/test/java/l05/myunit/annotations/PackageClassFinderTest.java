package l05.myunit.annotations;

import l05.myunit.testpackage.subpackage.TestClass;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class PackageClassFinderTest {
    @Test
    public void findClasses() {
        PackageClassFinder finder = new PackageClassFinder();
        try {
            List<Class<?>> classes = finder.getClasses("l05.myunit.testpackage");
            assertEquals(classes.get(0), TestClass.class);
        } catch (Exception e) {
            fail();
            throw new RuntimeException(e);
        }
    }
}
