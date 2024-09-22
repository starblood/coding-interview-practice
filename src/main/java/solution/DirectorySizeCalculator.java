package solution;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 파일 시스템의 Directory 크기 계산하는 프로그램을 작성하세요.
 *
 * 문제 설명:
 * 1. 주어진 파일 시스템에서 특정 Directory 의 총 크기를 계산하는 프로그램을 작성하세요.
 * 2. 파일 시스템은 directory 와 file 로 구성되어 있으며, 각 directory 는 하위 directory 와 file 을 가질 수 있습니다.
 * 3. 각 file 의 크기는 bytes 단위로 주어집니다.
 *
 * // method signature
 * public long calculateDirectorySize(String dir)
 *
 * 입력 예시:
 * 파일 시스템은 다음과 같은 구조를 가진다고 가정합니다.
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
 * 출력 예시:
 * String directoryPath = "/root";
 * long result = calculateDirectorySize(directoryPath); // result는 1500 이어야 합니다.
 *
 * 조건:
 * 1. directory 경로는 절대 경로로 주어집니다.
 * 2. directory 와 file 이름은 고유합니다.
 * 3. 재귀적 으로 하위 directory 의 크기를 모두 합산해야 합니다.
 *
 * Hints:
 * 1. Visitor Pattern 을 사용하여 해결 할 수 있습니다.
 * 2. 단순하게 Recursive 로 풀 수 있습니다.
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

