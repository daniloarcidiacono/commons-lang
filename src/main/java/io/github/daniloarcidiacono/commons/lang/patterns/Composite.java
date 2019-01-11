package io.github.daniloarcidiacono.commons.lang.patterns;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Base class for an object which aggregates a list of elements.
 * @param <T> type of the component
 */
public class Composite<T> {
    protected List<T> components = new ArrayList<>();

    public Composite() {
    }

    public Composite(final Collection<T> component) {
        components.addAll(component);
    }

    public Composite(final T ...component) {
        Collections.addAll(components, component);
    }

    public Composite<T> add(final T component) {
        components.add(component);
        return this;
    }

    public Composite<T> add(final T ...component) {
        Collections.addAll(components, component);
        return this;
    }

    /**
     * Adds a component before the specified one.
     * <p>If the specified component is not found, the component is <strong>not</strong> added.
     *
     * @param before the component to search for
     * @param component the component to add
     * @return the class instance, for chaining calls
     */
    public Composite<T> addBefore(final T before, final T component) {
        final int index = components.indexOf(before);
        if (index != -1) {
            components.add(index, component);
        }

        return this;
    }

    /**
     * Adds a component after the specified one.
     * <p>If the specified component is not found, the component is <strong>not</strong> added.
     *
     * @param after the component to search for
     * @param component the component to add
     * @return the class instance, for chaining calls
     */
    public Composite<T> addAfter(final T after, final T component) {
        final int index = components.indexOf(after);
        if (index != -1) {
            components.add(index + 1, component);
        }

        return this;
    }

    /**
     * Multi-component variant of {@link #addBefore(Object, Object)}
     * @param before the component to search for
     * @param component the components to add
     * @return the class instance, for chaining calls
     */
    public Composite<T> addBefore(final T before, final T ...component) {
        int index = components.indexOf(before);
        if (index != -1) {
            for (T t : component) {
                components.add(index, t);
                index++;
            }
        }

        return this;
    }

    /**
     * Multi-component variant of {@link #addAfter(Object, Object)}
     * @param after  the component to search for
     * @param component the components to add
     * @return the class instance, for chaining calls
     */
    public Composite<T> addAfter(final T after, final T ...component) {
        int index = components.indexOf(after);
        if (index != -1) {
            for (T t : component) {
                components.add(index + 1, t);
                index++;
            }
        }

        return this;
    }

    /**
     * Removes every component.
     * @return the class instance, for chaining calls
     */
    public Composite<T> removeAll() {
        components.clear();
        return this;
    }

    /**
     * Removes the specified component.
     * @param component the component to remove.
     * @return the class instance, for chaining calls
     */
    public Composite<T> remove(final T component) {
        components.remove(component);
        return this;
    }

    /**
     * Removes a set of components.
     * @param component the components to remoev
     * @return the class instance, for chaining calls
     */
    public Composite<T> remove(final T ...component) {
        for (T t : component) {
            components.remove(t);
        }

        return this;
    }

    /**
     * Searches for the component having the specified type.
     * @param clazz the return type class
     * @param <U> the return type
     * @return the first component having the specified type, or null if it is not found.
     */
    public <U extends T> U get(final Class<U> clazz) {
        for (T component : components) {
            if (component != null && component.getClass().equals(clazz)) {
                return (U)component;
            }
        }

        return null;
    }

    /**
     * Returns the list of components.
     * @return the list of components.
     */
    public List<T> getComponents() {
        return components;
    }
}
