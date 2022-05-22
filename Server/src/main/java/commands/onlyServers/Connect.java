package commands.onlyServers;

import collections.CommandCollection;
import commands.*;

import java.util.HashSet;

public class Connect extends CommandsToCollection {
    public Connect() {
        super("connect", CommandArgs.NO_ARGS, "resend hashmap to client with server's commands");
    }

    @Override
    public ServerResult function(String... args) {
        HashSet<CommandData> hashSet = new HashSet<>();
        for (AbstractCommand abstractCommand:CommandCollection.getInstance().getServerCollection().values()) {
            if(abstractCommand.getData().getName().equals("save"))continue;
            if(abstractCommand.getData().getName().equals("connect"))continue;
            if(abstractCommand.getData().getName().equals("login"))continue;
            if(abstractCommand.getData().getName().equals("register"))continue;
            hashSet.add(abstractCommand.getData());
        }
        return new ServerResult(hashSet,true);
    }
}
