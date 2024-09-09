package util;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;


    
    public class WriteBinary {
        public static void WriteBin(String bitsString, String filePath) {
    
            try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
                byte[] byteArray = bitStringToByteArray(bitsString);
                bos.write(byteArray);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        // Convert a bit string to a byte array using ByteBuffer for efficiency
        private static byte[] bitStringToByteArray(String bitString) {
            int length = bitString.length();
            int byteArraySize = (length + 7) / 8;
            ByteBuffer byteBuffer = ByteBuffer.allocate(byteArraySize);
    
            for (int i = 0; i < length; i++) {
                if (bitString.charAt(i) == '1') {
                    int byteIndex = i / 8;
                    int bitIndex = 7 - (i % 8);
                    byteBuffer.put(byteIndex, (byte) (byteBuffer.get(byteIndex) | (1 << bitIndex)));
                }
            }
    
            return byteBuffer.array();
        }
    }
    
