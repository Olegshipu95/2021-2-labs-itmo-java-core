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

public class Login extends User {
    public Login() {
        super("login", CommandArgs.LOGIN_ARGS, "Makes the user sign in");
    }

    @Override
    public ServerResult function(String... args) {
        try {
            ArrayList<String> message = new ArrayList<>();
            Connection connection = ConnectToDataBase.getConnection();
            String request = "select * from logins where login = ? and password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setString(1, args[0]);
            preparedStatement.setString(2, args[1]);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                message.add("Norm");
                return new ServerResult(message, true, true);
            }
            message.add("Takogo net");
            return new ServerResult(message, true, false);
        } catch (SQLException e) {
            throw new DataBaseException();
        }
    }
}