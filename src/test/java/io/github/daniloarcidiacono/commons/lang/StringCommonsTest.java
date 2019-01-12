package io.github.daniloarcidiacono.commons.lang;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for {@link StringCommons} class.
 */
class StringCommonsTest {
    @Test
    void hasLength() {
        assertEquals(StringCommons.hasLength(null), false, "Null has no length");
        assertEquals(StringCommons.hasLength(""), false, "Empty string has no length");
        assertEquals(StringCommons.hasLength("test"), true, "Non empty string has length");
    }

    @Test
    void capitalize() {
        assertEquals(StringCommons.capitalize(""), "");
        assertEquals(StringCommons.capitalize("test"), "Test", "Only first letter is capitalized");
        assertEquals(StringCommons.capitalize("TEST"), "TEST", "Only first letter is capitalized");
    }

    @Test
    void uncapitalize() {
        assertEquals(StringCommons.uncapitalize(""), "");
        assertEquals(StringCommons.uncapitalize("test"), "test", "Only first letter is uncapitalized");
        assertEquals(StringCommons.uncapitalize("TEST"), "tEST", "Only first letter is uncapitalized");
    }

    @Test
    void listToString() {
        assertEquals(StringCommons.listToString(Arrays.asList()), "{  }");
        assertEquals(StringCommons.listToString(Arrays.asList( "first" )), "{ first }");
        assertEquals(StringCommons.listToString(Arrays.asList( "first", "second" )), "{ first, second }");
    }

    @Test
    void quotes() {
        assertEquals(StringCommons.singleQuote("test"), "'test'");
        assertEquals(StringCommons.doubleQuote("test"), "\"test\"");
        assertEquals(StringCommons.quote("test", "*"), "*test*");
    }
}