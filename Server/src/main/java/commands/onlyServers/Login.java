package commands.onlyServers;

import commands.CommandArgs;
import commands.DataForArray;
import commands.ServerResult;
import connect.ConnectToDataBase;
import connect.OnlyServersCommands;

import java.util.ArrayList;

public class Login extends OnlyServersCommands {
    public Login() {
        super("login", CommandArgs.LOGIN_ARGS, "Makes the user sign in");
    }

    @Override
    public ServerResult function(DataForArray dataForArray) {

        ArrayList<String> message = new ArrayList<>();
        if (ConnectToDataBase.login(dataForArray.getArgs()[0], dataForArray.getArgs()[1])) {
            message.add("Norm");
            return new ServerResult(message, true, true);
        }
        message.add("takogo net");
        return new ServerResult(message,true,false);
    }
}