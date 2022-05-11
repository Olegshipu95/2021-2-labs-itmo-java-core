package commands;

import collections.CommandCollection;

public abstract class CommandsToCollection extends AbstractCommand {

    public CommandsToCollection(String name, CommandArgs commandArgs, String description) {
        super(name, commandArgs, description);
        if(!(CommandCollection.getInstance().getServerCollection().containsKey(name))){
            CommandCollection.getInstance().getServerCollection().put(name,this);
        }
    }
}
