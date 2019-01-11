package io.github.daniloarcidiacono.commons.lang;

import com.github.marschall.memoryfilesystem.MemoryFileSystemBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests for {@link FileCommons} class.
 * @see <a href="https://github.com/frohoff/jdk8u-jdk/blob/master/test/java/nio/file/Files/walkFileTree/CreateFileTree.java">jdk8u-jdk: CreateFileTree utility class</a>
 */
class FileCommonsTest {
    @Test
    void deleteFolder() throws IOException {
        try (final FileSystem fs = MemoryFileSystemBuilder.newEmpty().build()) {
            // Try to delete a non existing folder (must do nothing)
            FileCommons.deleteFolder(fs.getPath("not_existing"));

            // Create the folder that will be deleted
            final Path testFolder = fs.getPath("/test");
            Files.createDirectories(testFolder);
            Files.createFile(fs.getPath("/test/file1.txt"));
            Files.createFile(fs.getPath("/test/file2.txt"));
            Files.createFile(fs.getPath("/test/file3.txt"));

            // Create another folder
            final Path otherFolder = fs.getPath("/other");
            Files.createDirectories(otherFolder);
            Files.createFile(fs.getPath("/other/file1.txt"));

            // Create some symbolic links
            Files.createSymbolicLink(fs.getPath("/test/file1_link.txt"), fs.getPath("/other/file1.txt"));
            Files.createSymbolicLink(fs.getPath("/test/other_link"), fs.getPath("/other"));

            // Delete the folder
            FileCommons.deleteFolder(testFolder);

            // Try to delete a file (must do nothing)
            FileCommons.deleteFolder(fs.getPath("/other/file1.txt"));

            // https://docs.oracle.com/javase/tutorial/essential/io/walk.html
            // By default, walkFileTree does not follow symbolic links.
            final List<String> paths = new ArrayList<>();
            Files.walkFileTree(fs.getPath("/"), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    paths.add(file.toString());
                    return FileVisitResult.CONTINUE;
                }


                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    paths.add(dir.toString());
                    return FileVisitResult.CONTINUE;
                }
            });

            assertEquals(
                Arrays.asList("/", "/other", "/other/file1.txt"),
                paths,
                "Symbolic links should not be followed"
            );
        }
    }

    @Test
    void loadResource() {
        assertEquals("test file content", FileCommons.loadResource("test.txt"));
        assertEquals("", FileCommons.loadResource("test_empty.txt"));
        assertEquals(null, FileCommons.loadResource("test_not_existing.txt"));
    }
}