package problem;

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

    public long calculateDirectorySize(String dir) {
        // TODO: 구현
        return 0;
    }


    public static void main(String[] args) {
        String dir = args[0];
        DirectorySizeCalculator calculator = new DirectorySizeCalculator();

        long result = calculator.calculateDirectorySize(dir);
        System.out.println("Total size: " + result + " bytes");
    }
}

