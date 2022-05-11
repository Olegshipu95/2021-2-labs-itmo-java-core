

package commands.system;

import commands.*;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class Add extends CommandsToCollection {
    public Add() {
        super("add", CommandArgs.FILLING_ALL_ARGS_WITHOUT_ID,"add a new item to the collection.You should write:\n   string name,float x,Integer y,boolean realhero,boolean hasToothpick,Float impactSpeed,\n   Integer minutesOfWaiting,WeaponType weaponType,Mood mood,boolean bool" );
    }
    public ServerResult function(String ... arguments) {
        try {
            if (WriteTheValues.createObject(arguments)) {
                return new ServerResult(true);
            } else {
                return new ServerResult(false);
            }
        } catch (Exception e) {
            return new ServerResult(false);
        }
    }

}
