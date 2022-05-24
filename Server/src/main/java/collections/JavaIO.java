package collections;

import commands.WriteTheValues;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
/**
 * this class is using for communication with files(reading and writing)
 */
public class JavaIO {

    /**
     * this method writes a string to file
     * @param example
     */
    public static void writeToFile(String example) {
        File file = new File("/common/src/main/resources/text.txt");
        Scanner scanner = new Scanner(System.in);

        while(true) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                fileOutputStream.write(example.getBytes());
                fileOutputStream.close();
                return;
            } catch (IOException e) {
                System.out.println("Problem with the file, please enter a new file");
                if (!scanner.hasNext()) {
                    System.exit(0);
                }
                file = new File(scanner.nextLine());
            }
        }
    }
}