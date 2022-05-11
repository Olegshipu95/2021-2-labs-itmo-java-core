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

    public static void CSVCreateObject() {
        Scanner scanner = new Scanner(System.in);
        //File file = new File(GetFileFromResources.getInstance().getFilePathToSave("text2.txt"));
        String relative = (new File("")).getAbsolutePath() + File.separator + "/common/src/main/resources";
        String child = "text2.txt";
        new StringBuilder();

        while(true) {
            try {
                File path = new File(relative, child);
                CSVParser parser = CSVParser.parse(path, Charset.defaultCharset(), CSVFormat.RFC4180);
                List<CSVRecord> parserlocal = parser.getRecords();
                Iterator iterator = parserlocal.iterator();

                while(iterator.hasNext()) {
                    CSVRecord record = (CSVRecord)iterator.next();
                    if (record.size() == 16) {
                        WriteTheValues.createObject(record);
                    }
                }

                return;
            } catch (IOException e) {
                System.out.println("There were problems (does not exist or no rights) with the file");
                System.out.print("Enter the path to the file:");
                if (!scanner.hasNext()) {
                    System.exit(0);
                }

                relative = scanner.nextLine();
                String[] strings ;
                strings = relative.split("/");
                child = strings[strings.length-1];
            }
        }
    }
}