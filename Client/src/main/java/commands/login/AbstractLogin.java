package commands.login;

import clientServer.ConnectWithServer;
import clientServer.UsersLogin;
import collections.CommandCollection;
import commands.*;
import exceptions.IncorrectArgsException;

import java.io.IOException;

public abstract class AbstractLogin extends AbstractCommand {
    public AbstractLogin(String name, CommandArgs commandArgs, String description) {
        super(name, commandArgs, description);
        if(!(CommandCollection.getLoginCommands().containsKey(name))){
            CommandCollection.getLoginCommands().put(name,this);
        }
    }

    public String[] checkTypeArgs(String[] args) throws IncorrectArgsException {
        return ArgsValidator.argsValidator(getData().getCommandArgs(),args);
    }
    @Override
    public Result function(DataForArray dataForArray) {
        String[] args;
        try{
            args = checkTypeArgs(dataForArray.getArgs());
        } catch (IncorrectArgsException e) {
            e.getMessage();
            return new Result(false);
        }
        try {
            DataClients dataClients = new DataClients(this.getData().getName(), args);
            DataServer dataServer = ConnectWithServer.getInstance().connectWithServer(dataClients);
            if(dataServer.isStatus()) {
                UsersLogin.setName(args[0]);
                UsersLogin.setPassword(args[1]);
                return new Result(true);
            }
            else{
                for (String s: dataServer.getMessage()) {
                    System.out.println(s);
                }
                return new Result(false);
            }
        } catch (IOException e) {
            System.out.println("Troubles with server");
            return new Result(false);
        }
    }
}
