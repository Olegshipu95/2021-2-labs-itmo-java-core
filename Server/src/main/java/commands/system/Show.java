
package commands.system;

import collections.StackCollection;
import commands.*;
import entities.HumanBeing;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Show extends CommandsToCollection {
    public Show() {
        super("show", CommandArgs.NO_ARGS, "output to the standard output stream all the elements of the collection in a string representation");
    }

    public ServerResult function(DataForArray dataForArray) {
        Lock readLock = new ReentrantReadWriteLock().readLock();
        readLock.lock();
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
        finally {
            readLock.unlock();
        }
    }

}
