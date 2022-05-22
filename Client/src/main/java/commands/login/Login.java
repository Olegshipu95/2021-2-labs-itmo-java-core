package commands.login;

import clientServer.ConnectWithServer;
import clientServer.UsersLogin;
import commands.CommandArgs;
import commands.DataClients;
import commands.DataServer;
import commands.Result;
import exceptions.IncorrectArgsException;

import java.io.IOException;

public class Login extends AbstractLogin{
    public Login() {
        super("login", CommandArgs.LOGIN_ARGS, "Makes the user sign in");
    }
}
