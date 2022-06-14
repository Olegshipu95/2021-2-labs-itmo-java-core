package commands.onlyServers;

import commands.CommandArgs;
import commands.DataForArray;
import commands.ServerResult;
import connect.ConnectToDataBase;
import connect.OnlyServersCommands;
import exceptions.DataBaseException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Register extends OnlyServersCommands {
    public Register() {
        super("register", CommandArgs.LOGIN_ARGS, "Makes user registration");
    }

    @Override
    public ServerResult function(DataForArray dataForArray) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(dataForArray.getArgs()[1].getBytes());
            byte byteData[] = md.digest();
            StringBuffer hexString = new StringBuffer();
            for (byte aByteData : byteData) {
                String hex = Integer.toHexString(0xff & aByteData);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            ArrayList<String> message = new ArrayList<>();
            ResultSet resultSet;
            Connection connection = ConnectToDataBase.getConnection();
            String request = "INSERT INTO logins VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setString(1, dataForArray.getArgs()[0]);
            preparedStatement.setString(2, hexString.toString());
            try {
                preparedStatement.execute();
                resultSet = preparedStatement.getResultSet();
            } catch (SQLException e) {
                System.out.println("such user already exists");
                message.add("such user already exists");
                return new ServerResult(message,true,false);
            }
            System.out.println("user's added");
            message.add("user added");
            return new ServerResult(message, true, true);
        } catch (SQLException e) {
            e.printStackTrace();
            ArrayList<String> message = new ArrayList<>();
            message.add("Problem with server");
            return new ServerResult(message,true,false);
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            ArrayList<String> message = new ArrayList<>();
            message.add("Problema with server");
            return new ServerResult(message,true,false);
        }
    }
}
