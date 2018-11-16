package io.github.daniloarcidiacono.commons.lang;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Miscellaneous Java Reflection utility methods.
 * @author Danilo Arcidiacono
 */
public abstract class ReflectiveCommons {
    /**
     * Returns the methods of the specified class annotated with the specified annotation.
     * @param type the class type
     * @param annotationClass the annotation class type
     * @return the list of the class methods matching the annotation
     */
    public static List<Method> getMethodsAnnotatedWith(final Class<?> type, final Class<? extends Annotation> annotationClass) {
        final List<Method> methods = new ArrayList<>();
        Class<?> klass = type;

        // need to iterated thought hierarchy in order to retrieve methods from above the current instance
        while (klass != Object.class) {
            // iterate though the list of methods declared in the class represented by klass variable, and add those annotated with the specified annotation
            final List<Method> allMethods = Arrays.asList(klass.getDeclaredMethods());
            for (final Method method : allMethods) {
                if (method.isAnnotationPresent(annotationClass)) {
                    methods.add(method);
                }
            }

            // move to the upper class in the hierarchy in search for more methods
            klass = klass.getSuperclass();
        }

        return methods;
    }

    /**
     * Returns the list of the classes enclosing the specified class.
     * @param clazz the starting class
     * @return the list of the classes enclosing the specified class.
     */
    public static List<Class<?>> getEnclosingClasses(final Class<?> clazz) {
        final List<Class<?>> enclosingClasses = new ArrayList<>();

        Class<?> current = clazz;
        do {
            enclosingClasses.add(0, current);
            current = current.getEnclosingClass();
        } while (current != null);

        return enclosingClasses;
    }
}
