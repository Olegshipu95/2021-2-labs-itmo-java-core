package commands;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;

public class ServerResult extends Result{
    @Getter
    DataServer dataServer;
    public ServerResult(boolean command) {
        super(command);
        dataServer = new DataServer(getMessage());
    }
    public ServerResult(ArrayList<String> message,boolean command,boolean status){
        super(message, command);
        dataServer = new DataServer(getMessage(),status);
    }
    public ServerResult(ArrayList<String> message, boolean command) {
        super(message, command);
        dataServer = new DataServer(getMessage());
    }

    public ServerResult(HashSet<CommandData> hashSet, boolean command) {
        super(hashSet, command);
        dataServer = new DataServer(hashSet,getMessage());
    }
}
