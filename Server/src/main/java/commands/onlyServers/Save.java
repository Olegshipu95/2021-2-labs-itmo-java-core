package commands.onlyServers;

import collections.JavaIO;
import collections.StackCollection;
import commands.*;
import connect.OnlyServersCommands;
import entities.HumanBeing;
import java.util.ArrayList;
import java.util.Iterator;

public class Save extends OnlyServersCommands {
    public Save() {
        super("save", CommandArgs.NO_ARGS, "save the collection to a file");

    }

    public ServerResult function(DataForArray dataForArray) {

        try {
            Iterator iterator = StackCollection.entitiesCollection.iterator();

            while(iterator.hasNext()) {
                HumanBeing obj = (HumanBeing)iterator.next();
                JavaIO.writeToFile(obj.csvToString() + "\n");
            }
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("Success save in file");
            return new ServerResult(arrayList,true);
        }
        catch (Exception e) {
            return new ServerResult(false);
        }
    }


}
