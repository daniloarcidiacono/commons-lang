package io.github.daniloarcidiacono.commons.lang;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;

/**
 * Miscellaneous Java file utility methods.
 */
public abstract class FileCommons {
    /**
     * Deletes Folder with all of its content.
     * Symbolic links are NOT followed.
     * @param folder path to folder which should be deleted
     * @throws IOException
     */
    public static void deleteFolder(final Path folder) throws IOException {
        if (!Files.exists(folder) || !Files.isDirectory(folder)) {
            return;
        }

        // https://docs.oracle.com/javase/tutorial/essential/io/walk.html
        // By default, walkFileTree does not follow symbolic links.
        Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                if (exc != null) {
                    throw exc;
                }

                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    /**
     * Reads a text file from the given path.
     * @see <a href="https://stackoverflow.com/questions/6068197/utils-to-read-resource-text-file-to-string-java">Utils to read resource text file to String (Java)</a>
     * @param path the path to load
     * @return the file content, or null if could not be loaded.
     */
    public static String loadResource(final String path) {
        final InputStream resourceAsStream = FileCommons.class.getClassLoader().getResourceAsStream(path);
        if (resourceAsStream == null) {
            return null;
        }

        final Scanner scanner = new Scanner(resourceAsStream, "UTF-8").useDelimiter("\\A");

        if (scanner.hasNext()) {
            return scanner.next();
        }

        // Using scanner.next() on empty files throws NoSuchElementException
        return "";
    }
}
