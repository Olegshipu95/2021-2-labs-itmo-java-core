//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commands.system;

import clientServer.ConnectWithServer;
import collections.CommandCollection;
import commands.*;

import java.io.IOException;
import java.util.ArrayList;

public class Exit extends CommandsToCollection {
    public Exit() {
        super("exit", CommandArgs.NO_ARGS, "terminate the program (without saving to a file)");
    }

    public Result function(String ... args) {
        DataClients dataClients = new DataClients("save",args);
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
