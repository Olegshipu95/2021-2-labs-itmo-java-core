package commands.onlyServers;

import commands.CommandArgs;
import commands.Result;
import commands.ServerResult;
import connect.ConnectToDataBase;
import connect.User;
import exceptions.DataBaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Register extends User {
    public Register() {
        super("register", CommandArgs.LOGIN_ARGS, "Makes user registration");
    }

    @Override
    public ServerResult function(String... args) {
        try {
            ArrayList<String> message = new ArrayList<>();
            ResultSet resultSet;
            Connection connection = ConnectToDataBase.getConnection();
            String request = "INSERT INTO logins VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setString(1, args[0]);
            preparedStatement.setString(2, args[1]);
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
            throw new DataBaseException();
        }
    }
}
