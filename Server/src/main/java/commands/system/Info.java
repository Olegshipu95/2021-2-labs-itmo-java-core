

package commands.system;

import collections.InfoFail;
import collections.StackCollection;
import commands.*;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class Info extends CommandsToCollection {
    public Info() {
        super("info", CommandArgs.NO_ARGS, "output information about the collection (type, initialization date, number of items, etc.) to the standard output stream.");
    }

    public ServerResult function(String ... args) {
        try {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(InfoFail.infoCol(StackCollection.entitiesCollection.size()));
            return new ServerResult(arrayList,true);
        } catch (Exception e) {
            return new ServerResult(false);
        }
    }


}
