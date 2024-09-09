package util;

import java.io.BufferedInputStream;

import java.io.FileInputStream;

import java.io.IOException;


public class ReadBinary {


public static StringBuilder ReadBin(String filePath ) {

try {

    StringBuilder bitString = readBinaryFile(filePath);

    return bitString;

    } catch (IOException e) {

        e.printStackTrace();

        return new StringBuilder();

    }

}


public static StringBuilder readBinaryFile(String filePath) throws IOException {

    StringBuilder bitStringBuilder = new StringBuilder();

    try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath))) {

        byte[] buffer = new byte[1024]; // Buffer size of 8KB

        int bytesRead;


    while ((bytesRead = bis.read(buffer)) != -1) {

        for (int i = 0; i < bytesRead; i++) {

        bitStringBuilder.append(byteToBitString(buffer[i]));

        }   

}

}

return bitStringBuilder;


}

private static String byteToBitString(byte b) {

StringBuilder bitString = new StringBuilder(8);

for (int i = 7; i >= 0; i--) {

bitString.append((b >> i) & 1);

}

return bitString.toString();

}


}
