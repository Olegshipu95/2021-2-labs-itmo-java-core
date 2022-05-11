

package commands.system;

import collections.IdCollection;
import collections.StackCollection;
import commands.*;
import entities.HumanBeing;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Stack;

public class RemoveLower extends CommandsToCollection {
    public RemoveLower() {
        super("remove_lower", CommandArgs.ID_ARGS, "remove all items smaller than the specified one from the collection");
    }


    public ServerResult function(String... arguments) {
        try {
            int id = Integer.parseInt(arguments[0]);


            Stack clone = new Stack();

            while (StackCollection.entitiesCollection.size() > 0) {
                HumanBeing lol;
                if ((lol = (HumanBeing) StackCollection.entitiesCollection.pop()).getId() >= id) {
                    clone.push(lol);
                }
            }

            StackCollection.entitiesCollection = clone;
            return new ServerResult(true);
        } catch (Exception e) {
            return new ServerResult(false);
        }
    }
}
