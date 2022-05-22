package connect;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ConnectToDataBase {
    private static String url;
    private static String username;
    private static String password;
    @Getter
    private static Connection connection;
    public static void connect() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Write the path of script: ");
        while (scanner.hasNext()) {
            String filePath = scanner.nextLine();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
                url = reader.readLine();
                username = reader.readLine();
                password = reader.readLine();
            } catch (IOException e) {
                System.out.println("File is unreadable, write the path again pls");
            }
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, username, password);
                break;
            } catch (SQLException e) {
                System.out.print("data in file is incorrect, pls, write the path of new file: ");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
