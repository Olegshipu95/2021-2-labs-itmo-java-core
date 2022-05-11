

package commands.system;

import collections.HistoryCollection;
import commands.AbstractCommand;
import commands.CommandArgs;
import commands.CommandsToCollection;
import commands.Result;

import java.util.ArrayList;

public class History extends CommandsToCollection {
    public History() {
        super("history", CommandArgs.NO_ARGS, "output the last 14 commands (without their arguments)");
    }

    public Result function(String ... args) {


            System.out.println(HistoryCollection.historyCollectrion);
        return new Result(true);

    }


}
