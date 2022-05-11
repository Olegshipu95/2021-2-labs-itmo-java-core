
package commands.system;

import collections.StackCollection;
import commands.*;
import entities.HumanBeing;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Stack;

public class SumOfMinutesOfWaiting extends CommandsToCollection {
     public SumOfMinutesOfWaiting() {
        super("sumOfMinutesOfWaiting", CommandArgs.NO_ARGS, "print the sum of the values of the minutes Of Waiting field for all elements of the collection");
    }

    public ServerResult function(String ... arguments) {

        try {
            Long sum = 0L;
            Stack clone = new Stack();

            while(StackCollection.entitiesCollection.size() > 0) {
                sum = sum + (long)((HumanBeing)StackCollection.entitiesCollection.peek()).getMinutesOfWaiting();
                clone.push((HumanBeing)StackCollection.entitiesCollection.pop());
            }

            StackCollection.entitiesCollection = clone;
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("The sum of the minutesOfWaiting field values for all elements of the collection is" + sum);
            return new ServerResult(arrayList,true);
        } catch (Exception e) {
            return new ServerResult(false);
        }
    }
}
