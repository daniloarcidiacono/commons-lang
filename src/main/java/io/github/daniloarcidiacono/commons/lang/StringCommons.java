package io.github.daniloarcidiacono.commons.lang;

/**
 * Miscellaneous {@link String} utility methods.
 * Inspired by Spring Framework's StringUtil class.
 *
 * <p>This class delivers some simple functionality that should really be
 * provided by the core Java {@link String} and {@link StringBuilder}
 * classes.
 *
 * @author Danilo Arcidiacono
 */
public abstract class StringCommons {
    /**
     * Check that the given {@code String} is neither {@code null} nor of length 0.
     * <p>Note: this method returns {@code true} for a {@code String} that
     * purely consists of whitespace.
     * @param str the {@code String} to check (may be {@code null})
     * @return {@code true} if the {@code String} is not {@code null} and has length
     */
    public static boolean hasLength(final String str) {
        return (str != null && !str.isEmpty());
    }

    /**
     * Capitalize a {@code String}, changing the first letter to
     * upper case as per {@link Character#toUpperCase(char)}.
     * No other letters are changed.
     * @param str the {@code String} to capitalize
     * @return the capitalized {@code String}
     */
    public static String capitalize(final String str) {
        return changeFirstCharacterCase(str, true);
    }

    /**
     * Uncapitalize a {@code String}, changing the first letter to
     * lower case as per {@link Character#toLowerCase(char)}.
     * No other letters are changed.
     * @param str the {@code String} to uncapitalize
     * @return the uncapitalized {@code String}
     */
    public static String uncapitalize(final String str) {
        return changeFirstCharacterCase(str, false);
    }

    private static String changeFirstCharacterCase(String str, boolean capitalize) {
        if (!hasLength(str)) {
            return str;
        }

        char baseChar = str.charAt(0);
        char updatedChar;
        if (capitalize) {
            updatedChar = Character.toUpperCase(baseChar);
        }
        else {
            updatedChar = Character.toLowerCase(baseChar);
        }
        if (baseChar == updatedChar) {
            return str;
        }

        char[] chars = str.toCharArray();
        chars[0] = updatedChar;
        return new String(chars, 0, chars.length);
    }

    /**
     * Converts an Iterable structure to a string representation.
     *
     * @param collection elements
     * @return the comma separated elements in string format
     */
    public static String listToString(final Iterable collection) {
        final StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        String separator = "";
        for (Object element : collection) {
            sb.append(separator);
            sb.append(element.toString());
            separator = ", ";
        }

        sb.append(" }");
        return sb.toString();
    }
}
