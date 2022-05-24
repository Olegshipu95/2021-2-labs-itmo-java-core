package connect;

import collections.CommandCollection;
import commands.CommandArgs;
import commands.CommandsToCollection;

public abstract class OnlyServersCommands extends CommandsToCollection{

    public OnlyServersCommands(String name, CommandArgs commandArgs, String description) {
        super(name, commandArgs, description);
        CommandCollection.getInstance().getOnlyServers().add(name);
    }
}
