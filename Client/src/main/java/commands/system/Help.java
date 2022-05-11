
package commands.system;

import collections.CommandCollection;
import commands.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Help extends CommandsToCollection {
    public Help() {
        super("help", CommandArgs.NO_ARGS, "output help for available commands");
    }

    public Result function(String... args) {
        System.out.println("|.............Client's commands.............|");
        for (Entry<String, String> pair : CommandCollection.getClientCommands().entrySet()){
            String key = (String) pair.getKey();
            String value = (String) pair.getValue();
            System.out.println("|| " + key + " --> " + value);
        }
        if (!CommandCollection.getServerCommands().isEmpty()) {
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
