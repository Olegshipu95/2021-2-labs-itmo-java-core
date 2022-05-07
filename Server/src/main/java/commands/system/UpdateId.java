//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package commands.system;

import collections.IdCollection;
import collections.StackCollection;
import commands.*;
import entities.HumanBeing;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class UpdateId extends CommandsToCollection {
    public UpdateId() {
        super("updateId", CommandArgs.FILLING_ALL_ARGS, "update the value of a collection item whose id is equal to the specified one. You need write: \n   string name,float x,Integer y,boolean realhero,boolean hasToothpick,Float impactSpeed,\n   Integer minutesOfWaiting,WeaponType weaponType,Mood mood,boolean bool");
    }


    public ServerResult function(String... arguments) {
        if (StackCollection.entitiesCollection.isEmpty()) {
            System.out.println("Collection has no items");
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("Collection has no items");
            return new ServerResult(arrayList, false);
        }

        String[] local = arguments;
        int id;
        id = Integer.parseInt(arguments[0]);
        if (!IdCollection.idCollection.contains(id)) {
            System.out.println("Data is incorrect(id doesn't contains in IdCollection), write the command again ");
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("Data is incorrect(id doesn't contains in IdCollection), write the command again ");
            return new ServerResult(arrayList, false);
        }

        Stack clone = new Stack();
        while (StackCollection.entitiesCollection.size() > 0) {
            HumanBeing lol;
            if ((lol = (HumanBeing) StackCollection.entitiesCollection.pop()).getId() != id) {
                clone.push(lol);
            }
        }

        StackCollection.entitiesCollection = clone;
        WriteTheValues.createObject(arguments);
        return new ServerResult(true);

    }
}
