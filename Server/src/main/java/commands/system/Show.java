
package commands.system;

import collections.StackCollection;
import commands.*;
import entities.HumanBeing;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Stack;

public class Show extends CommandsToCollection {
    public Show() {
        super("show", CommandArgs.NO_ARGS, "output to the standard output stream all the elements of the collection in a string representation");
    }

    public ServerResult function(DataForArray dataForArray) {

        try {
            ArrayList<String> arrayList = new ArrayList<>();
            Stack clone = new Stack();
            while(StackCollection.entitiesCollection.size() > 0) {
                HumanBeing local = (HumanBeing)StackCollection.entitiesCollection.pop();
                arrayList.add(local.toString());
                clone.push(local);
            }

            StackCollection.entitiesCollection = clone;
            arrayList.add("Command has successfully done");
            return new ServerResult(arrayList,true);
        } catch (Exception e) {
            return new ServerResult(false);
        }
    }

}
