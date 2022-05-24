package connect;

import commands.ServerResult;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
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

    public static boolean login(String username, String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte byteData[] = md.digest();
            StringBuffer hexString = new StringBuffer();
            for (byte aByteData : byteData) {
                String hex = Integer.toHexString(0xff & aByteData);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            Connection connection = ConnectToDataBase.getConnection();
            String request = "select * from logins where login = ? and password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hexString.toString());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }
}
