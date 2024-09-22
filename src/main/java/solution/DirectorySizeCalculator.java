package solution;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Write a program to calculate the size of a directory in the file system.
 *
 * Problem Description:
 *
 * Write a program that calculates the total size of a given directory in the file system.
 * The file system consists of directories and files, where each directory can contain subdirectories and files.
 * The size of each file is given in bytes.
 *
 * // method signature
 * public long calculateDirectorySize(String dir);
 *
 * Input Example: Assume the file system has the following structure:
 * /root
 * |-- /subdir1
 * |   |-- file1.txt (100 bytes)
 * |   |-- file2.txt (200 bytes)
 * |-- /subdir2
 * |   |-- file3.txt (300 bytes)
 * |   |-- /subsubdir1
 * |       |-- file4.txt (400 bytes)
 * |-- file5.txt (500 bytes)
 *
 * Output Example:
 * String directoryPath = "/root";
 * long result = calculateDirectorySize(directoryPath); // The result should be 1500.
 *
 * Conditions:
 * 1. The directory path is given as an absolute path.
 * 2. Directory and file names are unique.
 * 3. You must recursively sum the sizes of all subdirectories.
 *
 * Hints:
 * 1. You can solve this using the Visitor Pattern.
 * 2. A simple recursive approach can also be used.
 */
public class DirectorySizeCalculator {

    public long calculateDirectorySize1(String dir) {
        Path path = Paths.get(dir);
        DirectorySizeVisitor visitor = new DirectorySizeVisitor();
        try {
            Files.walkFileTree(path, visitor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return visitor.getTotalSize();
    }

    private static class DirectorySizeVisitor extends SimpleFileVisitor<Path> {
        private long totalSize = 0;

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            totalSize += attrs.size();
            return FileVisitResult.CONTINUE;
        }

        public long getTotalSize() {
            return totalSize;
        }
    }

    public long calculateDirectorySize2(String dir) {
        return calculateDirectorySizeRecursive(new File(dir));
    }

    private long calculateDirectorySizeRecursive(File directory) {
        long totalSize = 0;
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    totalSize += file.length();
                } else if (file.isDirectory()) {
                    totalSize += calculateDirectorySizeRecursive(file);
                }
            }
        }
        return totalSize;
    }

    public static void main(String[] args) {
        String dir = args[0];
        DirectorySizeCalculator calculator = new DirectorySizeCalculator();

        long result1 = calculator.calculateDirectorySize1(dir);
        long result2 = calculator.calculateDirectorySize2(dir);
        System.out.println("Total size: " + result1 + " bytes");
        System.out.println("Total size: " + result2 + " bytes");
    }
}

