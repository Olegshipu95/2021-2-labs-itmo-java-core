package commands.login;

import clientServer.ConnectWithServer;
import commands.CommandArgs;
import commands.DataClients;
import commands.DataServer;
import commands.Result;
import exceptions.IncorrectArgsException;

import java.io.IOException;

public class Register extends AbstractLogin{
    public Register() {
            super("register", CommandArgs.LOGIN_ARGS, "Makes user registration");
    }
}
