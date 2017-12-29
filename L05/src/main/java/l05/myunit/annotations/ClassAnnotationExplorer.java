package l05.myunit.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ClassAnnotationExplorer {
    private Class<?> klass;

    public ClassAnnotationExplorer(Class<?> klass) {
        this.klass = klass;
    }

    public Method[] getAnnotatedMethods(Class<? extends Annotation> annotation) {

        return Arrays.stream(klass.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotation))
                .toArray(Method[]::new);
    }

    public Object getInstance() {
        try {
            return klass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Method getFirstAnnotatedMethod(Class<? extends Annotation> annotation) {
        Method[] methods = getAnnotatedMethods(annotation);
        return methods.length > 0 ? methods[0] : null;
    }

    public boolean hasAnnotatedMethod(Class<? extends Annotation> annotation) {
        return getFirstAnnotatedMethod(annotation) != null;
    }
}
