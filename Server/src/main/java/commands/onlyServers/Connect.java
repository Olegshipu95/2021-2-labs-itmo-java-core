package commands.onlyServers;

import collections.CommandCollection;
import commands.*;
import connect.OnlyServersCommands;

import java.util.HashSet;

public class Connect extends OnlyServersCommands {
    public Connect() {
        super("connect", CommandArgs.NO_ARGS, "resend hashmap to client with server's commands");
    }

    @Override
    public ServerResult function(DataForArray dataForArray) {
        HashSet<CommandData> hashSet = new HashSet<>();
        for (AbstractCommand abstractCommand:CommandCollection.getInstance().getServerCollection().values()) {
            if(CommandCollection.getInstance().getOnlyServers().contains(abstractCommand.getData().getName()))continue;
            hashSet.add(abstractCommand.getData());
        }
        return new ServerResult(hashSet,true);
    }
}
