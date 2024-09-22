package solution;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Snappy 는 Google 에서 개발한 빠른 압축 및 해제압축 라이브러리입니다.
 * Snappy 는 가능한 한 높은 압축률을 달성하는 대신 속도와 압축 효율성의 균형을 맞추는 것을 목표로 하며,
 * 높은 처리량과 낮은 지연 시간을 지향합니다.
 *
 * Snappy 압축의 핵심 개념
 *
 * 1. Block 기반 처리:
 *   * Snappy 는 전체 Data Stream 이 아닌 Data block 단위로 작업합니다.
 *   * 이를 통해 Data 를 chunk 로 압축할 수 있으며, 병렬 처리가 용이하고 메모리 사용량을 줄일 수 있습니다.
 *
 * 2. Literal 및 Copy Command:
 *   * 알고리즘은 Byte Sequence(Literal) 와 반복 패턴(Copy)을 식별합니다.
 *   * 리터럴: 압축이 잘 되지 않는 Byte Sequence. 이러한 Sequence 는 그대로 저장됩니다.
 *   * 복사: Data 에서 이미 본 Sequence 를 참조하여 저장합니다.
 *
 * 3. Hash Table for Matching:
 *   * Snappy 는 Hash Table 을 사용하여 Byte Sequence 의 Match 를 빠르게 찾습니다.
 *   * Sequence 가 발견되면 Hash Table 을 사용하여 이전에 본 Data 에서 잠재적인 Match 를 찾습니다.
 *
 * 4. Varint Encoding:
 *   * Snappy 는 길이와 Offset 에 대해 variable-length integer(varint) 인코딩을 사용하여 작은 숫자에 대해 공간을 절약하고 효율적인 저장을 가능하게 합니다.
 *
 * Compression Steps
 *
 * 1. Initialization
 *   * 입력 데이터를 블록으로 나눕니다. 각 블록은 독립적으로 처리됩니다.
 *
 * 2. Processing Each Block
 *   * Sliding Window 방식을 사용하여 블록의 현재 위치에서 Match 를 찾습니다.
 *   * 현재 Byte Sequence 를 Hash 하여 Hash Table 에서 잠재적인 Match 를 찾습니다.
 *   * Match 가 발견되면 Match 길이와 Offset 을 복사 명령으로 기록합니다.
 *   * Match 가 없으면 Byte 를 Literal 로 기록합니다.
 *   * Hash Table 은 새로운 Sequence 로 업데이트됩니다.
 *
 * 3. Output Encoding
 *   * Literal 과 Copy 를 varint Encoding 을 사용하여 Encoding 합니다.
 *   * Copy 명령은 Matching Sequence 에 대한 Offset 과 Match 길이를 저장합니다.
 *   * Encoding 된 Literal 과 Copy 는 출력 Buffer 에 기록됩니다.
 *
 * 4. Finalization
 *   * 모든 블록이 처리되면 압축된 Data 가 출력됩니다.
 *
 *
 * Decompression Steps
 *
 * 1. Initialization:
 *   * 압축된 데이터를 블록 단위로 읽습니다.
 *
 * 2. Processing Each Block
 *   * Decoder 는 다음 Command(Literal 또는 Copy)을 읽습니다.
 *   * Literal 의 경우, Byte 는 직접 출력에 복사됩니다.
 *   * Copy 의 경우, Offset 과 길이가 Decoding 되고 해당 Byte 는 이전에 압축해제된 Data 에서 복사됩니다.
 *
 * 3. Finalization
 *   * 모든 블록이 처리되면 압축해제된 데이터가 출력됩니다.
 */
public class Snappy {
    private static final int MAX_OFFSET = 65536; // Maximum offset for back-references

    public static void main(String[] args) {
        String input = "This is an example text that we will compress and decompress using test.answer.Snappy. test.answer.Snappy is very fast.";
        byte[] inputBytes = input.getBytes();

        byte[] compressed = compress(inputBytes);
        System.out.println("Compressed: " + Arrays.toString(compressed));

        byte[] decompressed = decompress(compressed);
        String output = new String(decompressed);
        System.out.println("Decompressed: " + output);
    }

    /**
     * Allocates a buffer to hold the compressed data.
     * Iterates through the input data to find matches.
     * Encodes matches as copy commands and literals directly.
     * @param input
     * @return
     */
    public static byte[] compress(byte[] input) {
        ByteBuffer output = ByteBuffer.allocate(input.length * 2); // Allocate more than needed

        int inputIndex = 0;
        while (inputIndex < input.length) {
            int length = findMatchLength(input, inputIndex);
            if (length >= 4) { // Minimum length for a match to be useful
                int offset = findMatchOffset(input, inputIndex, length);
                encodeCopy(output, length, offset); // TODO answer 1
                inputIndex += length;
            } else {
                output.put(input[inputIndex++]);
            }
        }

        byte[] result = new byte[output.position()];
        output.rewind();
        output.get(result); // copy output buffer to result array
        return result;
    }

    /**
     * Allocates a buffer to hold the decompressed data.
     * Iterates through the compressed data to decode commands and reconstruct the original data.
     * @param input
     * @return
     */
    public static byte[] decompress(byte[] input) {
        ByteBuffer output = ByteBuffer.allocate(input.length * 2); // Allocate more than needed

        int inputIndex = 0;
        while (inputIndex < input.length) {
            byte b = input[inputIndex++];
            if ((b & 0x80) != 0) { // Copy command
                int length = (b & 0x7F) + 4;
                int offset = (input[inputIndex++] & 0xFF) | ((input[inputIndex++] & 0xFF) << 8);
                copyFromOutput(output, offset, length);
            } else { // Literal byte
                output.put(b); // TODO answer 2
            }
        }

        byte[] result = new byte[output.position()];
        output.rewind();
        output.get(result);
        return result;
    }

    /**
     * Finds the length of matching data segments.
     * @param input 입력 데이터
     * @param index data 에서 match 를 찾기 시작할 위치
     * @return length of match
     */
    private static int findMatchLength(byte[] input, int index) {
        int maxLength = input.length - index;
        int matchLength = 0;
        for (int i = 0; i < maxLength - 4; i++) {
            // TODO answer 3
            if (input[index + i] == input[index + i + 4]) {
                matchLength++;
            } else {
                break;
            }
        }
        return matchLength;
    }

    /**
     * Finds the offset of the matching data segment.
     * @param input 입력 데이터
     * @param index 찾기 시작할 위치
     * @param length match 의 길이
     * @return offset of match
     */
    private static int findMatchOffset(byte[] input, int index, int length) {
        for (int i = Math.max(0, index - MAX_OFFSET); i < index; i++) { // TODO answer 4
            // TODO answer 5
            if (Arrays.equals(Arrays.copyOfRange(input, i, i + length), Arrays.copyOfRange(input, index, index + length))) {
                return index - i;
            }
        }
        return 0;
    }

    /**
     * Encodes a copy command for repeated data segments.
     * @param output
     * @param length
     * @param offset
     */
    private static void encodeCopy(ByteBuffer output, int length, int offset) {
        output.put((byte) (0x80 | (length - 4))); // Encode length
        output.put((byte) (offset & 0xFF)); // Encode offset
        output.put((byte) ((offset >> 8) & 0xFF)); // Encode offset
    }

    /**
     * Copies previously decompressed data to the current position.
     * @param output
     * @param offset
     * @param length
     */
    private static void copyFromOutput(ByteBuffer output, int offset, int length) {
        int position = output.position();
        for (int i = 0; i < length; i++) {
            output.put(output.get(position - offset + i)); // TODO answer 6
        }
    }
}

