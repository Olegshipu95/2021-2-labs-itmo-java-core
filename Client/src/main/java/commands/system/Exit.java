
package commands.system;

import clientServer.ConnectWithServer;
import collections.CommandCollection;
import commands.*;
import exceptions.IncorrectArgsException;

import java.io.IOException;
import java.util.ArrayList;

public class Exit extends CommandsToCollection {
    public Exit() {
        super("exit", CommandArgs.NO_ARGS, "terminate the program (without saving to a file)");
    }

    public Result function(DataForArray dataForArray) {
        try {
            checkTypeArgs(dataForArray.getArgs());
        } catch (IncorrectArgsException e) {
            e.getMessage();
            return new Result(false);
        }
        DataClients dataClients = new DataClients("save", dataForArray.getArgs());
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            DataServer dataServer = ConnectWithServer.getInstance().connectWithServer(dataClients);
            System.out.println(dataServer.getMessage());
        } catch (IOException e) {
            System.out.println("Trouble with server. Save did not competed");
        }
        System.exit(0);
        return null;
    }

}
