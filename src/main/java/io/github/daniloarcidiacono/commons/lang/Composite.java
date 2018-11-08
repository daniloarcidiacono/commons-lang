package io.github.daniloarcidiacono.commons.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Base class for an object which aggregates a list of elements.
 * @param <T> type of the component
 *
 * @author Danilo Arcidiacono
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

    public Composite<T> removeAll() {
        components.clear();
        return this;
    }

    public Composite<T> add(final T component) {
        components.add(component);
        return this;
    }

    public Composite<T> add(final T ...component) {
        Collections.addAll(components, component);
        return this;
    }

    public Composite<T> addBefore(final T before, final T component) {
        final int index = components.indexOf(before);
        if (index != -1) {
            components.add(index, component);
        }

        return this;
    }

    public Composite<T> addAfter(final T after, final T component) {
        final int index = components.indexOf(after);
        if (index != -1) {
            components.add(index + 1, component);
        }

        return this;
    }

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

    public Composite<T> remove(final T component) {
        components.remove(component);
        return this;
    }

    public Composite<T> remove(final T ...component) {
        for (T t : component) {
            components.remove(t);
        }

        return this;
    }

    public <U extends T> U get(final Class<U> clazz) {
        for (T component : components) {
            if (component.getClass().equals(clazz)) {
                return (U)component;
            }
        }

        return null;
    }

    public List<T> getComponents() {
        return components;
    }
}
