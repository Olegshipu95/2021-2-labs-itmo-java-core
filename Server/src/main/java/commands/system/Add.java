

package commands.system;

import collections.StackCollection;
import commands.*;
import entities.HumanBeing;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.Arrays;

public class Add extends CommandsToCollection {
    public Add() {
        super("add", CommandArgs.FILLING_ALL_ARGS_WITHOUT_ID,"add a new item to the collection.You should write:\n   string name,float x,Integer y,boolean realhero,boolean hasToothpick,Float impactSpeed,\n   Integer minutesOfWaiting,WeaponType weaponType,Mood mood,boolean bool" );
    }
    public ServerResult function(String ... arguments) {
        try {
            HumanBeing humanBeing = WriteTheValues.createObject(arguments);
            StackCollection.getEntitiesCollection().push(humanBeing);
            return new ServerResult(true);
        } catch (SQLException e) {
            e.getMessage();
            return new ServerResult(false);
        }
    }

}
