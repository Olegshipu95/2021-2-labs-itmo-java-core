

package commands.system;

import collections.IdCollection;
import collections.StackCollection;
import commands.*;
import entities.HumanBeing;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class RemoveById extends CommandsToCollection {
    public RemoveById() {
        super("remove_by_id", CommandArgs.ID_ARGS, "delete an item from the collection by its id");
    }

    public ServerResult function(String... arguments) {
        int id = Integer.parseInt(arguments[0]);

        Stack clone = new Stack();
        //StackCollection.getEntitiesCollection().removeIf(x -> x.getId()==id);
        List<HumanBeing> list = StackCollection.getEntitiesCollection().stream().filter(x->x.getId()==id).collect(Collectors.toCollection(ArrayList::new));
        StackCollection.getEntitiesCollection().removeAll(list);
//        while (StackCollection.entitiesCollection.size() > 0) {
//            HumanBeing lol;
//            if (id != (lol = (HumanBeing) StackCollection.entitiesCollection.pop()).getId()) {
//                clone.push(lol);
//            }
//        }

        //StackCollection.entitiesCollection = clone;
        return new ServerResult(true);
    }
}
