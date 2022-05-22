package connect;

import commands.CommandArgs;
import commands.CommandsToCollection;

public abstract class User extends CommandsToCollection{
    private static String name;

    public User(String name, CommandArgs commandArgs, String description) {
        super(name, commandArgs, description);
    }
}
