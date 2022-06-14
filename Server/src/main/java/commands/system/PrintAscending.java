

package commands.system;

import collections.StackCollection;
import commands.*;
import entities.HumanBeing;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PrintAscending extends CommandsToCollection {
    public PrintAscending() {
        super("printAscending", CommandArgs.NO_ARGS, "output the elements of the collection in ascending order");
    }

    public ServerResult function(DataForArray dataForArray) {
        ArrayList<String> arrayList = new ArrayList<>();
        Lock readLock = new ReentrantReadWriteLock().readLock();
        readLock.lock();
        try {

            StackCollection.entitiesCollection.sort(new Comparator<HumanBeing>() {
                public int compare(HumanBeing o1, HumanBeing o2) {
                    return o1.getId() - o2.getId();
                }
            });
            Iterator iterator = StackCollection.entitiesCollection.iterator();

            while(iterator.hasNext()) {
                HumanBeing obj = (HumanBeing)iterator.next();
                arrayList.add(obj.toString());
            }
            arrayList.add("Command successfully executed");
            return new ServerResult(arrayList,true);
        } catch (Exception e) {
            return new ServerResult(false);
        }
        finally {
            readLock.unlock();
        }
    }

}
