

package commands.system;

import collections.HistoryCollection;
import commands.*;
import exceptions.IncorrectArgsException;

import java.util.ArrayList;

public class History extends CommandsToCollection {
    public History() {
        super("history", CommandArgs.NO_ARGS, "output the last 14 commands (without their arguments)");
    }

    public Result function(DataForArray dataForArray) {
        try {
            checkTypeArgs(dataForArray.getArgs());
        } catch (IncorrectArgsException e) {
            e.getMessage();
            return new Result(false);
        }
        System.out.println(HistoryCollection.historyCollectrion);
        return new Result(true);

    }


}
