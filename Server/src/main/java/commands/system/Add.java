

package commands.system;

import collections.StackCollection;
import commands.*;
import entities.HumanBeing;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Add extends CommandsToCollection {
    public Add() {
        super("add", CommandArgs.FILLING_ALL_ARGS_WITHOUT_ID,"add a new item to the collection." );
    }
    public ServerResult function(DataForArray dataForArray) {
        Lock writeLock = new ReentrantReadWriteLock().writeLock();
        writeLock.lock();
        try {
            HumanBeing humanBeing = WriteTheValues.createObject(dataForArray);
            StackCollection.getEntitiesCollection().push(humanBeing);
            return new ServerResult(true);
        } catch (SQLException e) {
            e.printStackTrace();
            e.getMessage();
            return new ServerResult(false);
        }
        finally {
            writeLock.unlock();
        }
    }

}
