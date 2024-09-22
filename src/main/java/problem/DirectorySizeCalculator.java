package problem;

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

    public long calculateDirectorySize(String dir) {
        // TODO: implements
        return 0;
    }


    public static void main(String[] args) {
        String dir = args[0];
        DirectorySizeCalculator calculator = new DirectorySizeCalculator();

        long result = calculator.calculateDirectorySize(dir);
        System.out.println("Total size: " + result + " bytes");
    }
}
