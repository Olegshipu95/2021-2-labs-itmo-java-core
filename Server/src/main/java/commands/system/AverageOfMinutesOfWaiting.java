//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commands.system;

import collections.StackCollection;
import commands.*;
import entities.HumanBeing;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Stack;

import static commands.CommandArgs.NO_ARGS;

public class AverageOfMinutesOfWaiting extends CommandsToCollection {
    public AverageOfMinutesOfWaiting() {
        super("averageOfMinutes", NO_ARGS, "output the average value of the minutes Of Waiting field for all items in the collection");
    }

    public ServerResult function(String ... arguments) {

        try {
            Long sum = 0L;
            int longArray = StackCollection.entitiesCollection.size();
            Stack clone = new Stack();

            while(StackCollection.entitiesCollection.size() > 0) {
                sum = sum + (long)((HumanBeing)StackCollection.entitiesCollection.peek()).getMinutesOfWaiting();
                clone.push((HumanBeing)StackCollection.entitiesCollection.pop());
            }

            StackCollection.entitiesCollection = clone;
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("The output of the average value of the minutesOfWaiting field for all elements of the collection is " + (sum / (long)longArray));
            return new ServerResult(arrayList,true);
        } catch (Exception var5) {
            return new ServerResult(false);
        }
    }

}
