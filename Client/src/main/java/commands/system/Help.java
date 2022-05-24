
package commands.system;

import clientServer.UsersLogin;
import collections.CommandCollection;
import commands.*;
import exceptions.IncorrectArgsException;

import java.util.Map.Entry;

public class Help extends CommandsToCollection {
    public Help() {
        super("help", CommandArgs.NO_ARGS, "output help for available commands");
    }

    public Result function(DataForArray dataForArray) {
        try {
            checkTypeArgs(dataForArray.getArgs());
        } catch (IncorrectArgsException e) {
            e.getMessage();
            return new Result(false);
        }
        System.out.println("|.............Client's commands.............|");
        for (Entry<String, AbstractCommand> pair : CommandCollection.getClientCommands().entrySet()){
            String key =  pair.getKey();
            AbstractCommand value =  pair.getValue();
            System.out.println("|| " + key + " --> " + value.getData().getDescription());
        }
        if(!CommandCollection.getServerCommands().isEmpty() && UsersLogin.getName()==null){
            System.out.println("|..............Login's commands.............|");
            for (Entry<String, AbstractCommand> pair : CommandCollection.getLoginCommands().entrySet()){
                String key = pair.getKey();
                AbstractCommand value = pair.getValue();
                System.out.println("|| " + key + " --> " + value.getData().getDescription());
            }
        }
        if (!CommandCollection.getServerCommands().isEmpty()&& UsersLogin.getName()!=null) {
            System.out.println("|.............Server's commands.............|");
            for (Entry<String, CommandData> pair : CommandCollection.getServerCommands().entrySet()){
                String key = pair.getKey();
                CommandData value = pair.getValue();
                System.out.println("|| " + key + " --> " + value.getDescription());
            }
        }
        return new Result(true);
    }
}
