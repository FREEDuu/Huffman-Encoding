
package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteText {

public static void write(String fileName, String to_woString)
    throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(to_woString);
        
        writer.close();
    }



}