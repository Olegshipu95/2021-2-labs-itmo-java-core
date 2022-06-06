package collections;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class InfoFail {
    public static String info;

    public static void readFile() {
        String path = (new File("")).getAbsolutePath();
        String outputFileName = path + File.separator + "/common/src/main/resources/info.txt";
        StringBuilder line = new StringBuilder();
        BufferedReader reader;
        Scanner scanner = new Scanner(System.in);
        try {
            reader = new BufferedReader(new FileReader(outputFileName));
        }catch(IOException e) {
            System.out.println("Problem with file");
            while (true) {
                System.out.print("Please enter the path: ");
                if (!scanner.hasNext()) {
                    System.out.println("Wrong input, forced shutdown");
                    System.exit(1);
                }
                outputFileName = scanner.nextLine();
            }
        }
            String local;
        try {
            while ((local = reader.readLine()) != null) {
                line.append(local + "\n");
            }
            reader.close();
        }
        catch (IOException e) {
            System.out.println("Проблемы с файлом");
        }
        if (line.length() <= 0) {
            System.out.println("Problems with the collection information file");
        }
        info = line.toString();
    }

    public static String    infoCol(int x) {
        StringBuilder line = new StringBuilder(info);
        line.append("Collection length:" + x);
        return line.toString().trim();
    }
}
