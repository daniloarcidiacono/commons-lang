package io.github.daniloarcidiacono.commons.lang.patterns;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link Composite} class.
 */
class CompositeTest {
    @Test
    public void testAddBefore() {
        final Composite<Integer> composite = new Composite<>();
        composite.add(2);
        composite.add(3);
        composite.addBefore(2, 1);
        composite.addBefore(1000, 10);

        assertEquals(
            Arrays.asList(1, 2, 3),
            composite.getComponents()
        );

        composite.addBefore(1, -3, -2, -1, 0);
        composite.addBefore(1000, -3, -2, -1, 0);

        assertEquals(
            Arrays.asList(-3, -2, -1, 0, 1, 2, 3),
            composite.getComponents()
        );
    }

    @Test
    public void testAddAfter() {
        final Composite<Integer> composite = new Composite<>();
        composite.add(1);
        composite.add(2);
        composite.add(10);
        composite.addAfter(2, 3);
        composite.addAfter(1000, 10);

        assertEquals(
            Arrays.asList(1, 2, 3, 10),
            composite.getComponents()
        );

        composite.addAfter(3, 4, 5, 6, 7, 8, 9);
        composite.addAfter(1000, 4, 5, 6, 7, 8, 9);

        assertEquals(
            Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
            composite.getComponents()
        );
    }

    @Test
    public void testRemove() {
        final Composite<Integer> composite = new Composite<>();
        composite.add(1, 2, 3);
        composite.remove(2);

        assertEquals(
            Arrays.asList(1, 3),
            composite.getComponents()
        );

        composite.removeAll();
        assertTrue(composite.getComponents().isEmpty());
    }

    @Test
    public void testMultiRemove() {
        final Composite<Integer> composite = new Composite<>(
            Arrays.asList(1, 2, 3, 4, 5)
        );

        composite.remove(2, 4);
        assertEquals(
            Arrays.asList(1, 3, 5),
            composite.getComponents()
        );
    }

    private static class Base {
    }

    private static class DerivedA extends Base {
    }

    private static class DerivedB extends Base {
    }

    private static class DerivedC extends Base {
    }

    @Test
    public void testGet() {
        final Base a = new Base();
        final Base b = new DerivedA();
        final Base c = new DerivedB();
        final Composite<Base> composite = new Composite<>(a, b, c, null);

        assertEquals(composite.get(Base.class), a);
        assertEquals(composite.get(DerivedA.class), b);
        assertEquals(composite.get(DerivedB.class), c);
        assertEquals(composite.get(DerivedC.class), null);
    }
}