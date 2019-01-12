package io.github.daniloarcidiacono.commons.lang;

import org.junit.jupiter.api.Test;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for {@link ReflectiveCommons} class.
 */
class ReflectiveCommonsTest {
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD})
    @Documented
    @interface TestAnnotation {
    }

    static class Base {
        public Base() {
        }

        @TestAnnotation
        public void myBaseMethod() {
        }

        public void myOtherBaseMethod() {
        }
    }

    static class Derived extends Base {
        public Derived() {
            super();
        }

        @TestAnnotation
        public void myDerivedMethod() {
        }

        public void myOtherDerivedMethod() {
        }
    }

    @Test
    void getMethodsAnnotatedWith() throws NoSuchMethodException {
        final List<Method> annotatedMethods = ReflectiveCommons.getMethodsAnnotatedWith(Derived.class, TestAnnotation.class);

        assertTrue(
            annotatedMethods.contains(Derived.class.getMethod("myDerivedMethod")),
            "Annotated methods are picked"
        );

        assertTrue(
            annotatedMethods.contains(Base.class.getMethod("myBaseMethod")),
    "Annotated methods from superclasses are picked"
        );

        assertFalse(
            annotatedMethods.contains(Derived.class.getMethod("myOtherBaseMethod")),
                "Methods without the specified annotation are not picked"
        );

        assertFalse(
            annotatedMethods.contains(Derived.class.getMethod("myOtherDerivedMethod")),
            "Methods without the specified annotation are not picked"
        );
    }

    @Test
    void getEnclosingClasses() {
        assertEquals(
            Arrays.asList(ReflectiveCommonsTest.class, Base.class),
            ReflectiveCommons.getEnclosingClasses(Base.class)
        );
    }
}