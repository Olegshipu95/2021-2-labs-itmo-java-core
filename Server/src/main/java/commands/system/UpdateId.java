

package commands.system;

import collections.IdCollection;
import collections.StackCollection;
import commands.*;
import connect.ConnectToDataBase;
import entities.HumanBeing;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class UpdateId extends CommandsToCollection {
    public UpdateId() {
        super("updateId", CommandArgs.FILLING_ALL_ARGS, "update the value of a collection item whose id is equal to the specified one.");
    }


    public ServerResult function(DataForArray dataForArray) {
        if (StackCollection.entitiesCollection.isEmpty()) {
            System.out.println("Collection has no items");
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("Collection has no items");
            return new ServerResult(arrayList, false);
        }
        String[] arguments = dataForArray.getArgs();
        int id;
        id = Integer.parseInt(arguments[0]);
        List<HumanBeing> list = StackCollection.getEntitiesCollection().stream().filter(x -> x.getId() == id).collect(Collectors.toCollection(ArrayList::new));
        if (list.isEmpty()) {
            System.out.println("Data is incorrect(id doesn't contains in IdCollection), write the command again ");
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("Data is incorrect(id doesn't contains in IdCollection), write the command again ");
            return new ServerResult(arrayList, false);
        }

        try {
            HumanBeing humanBeing = WriteTheValues.updateObject(arguments);
            Collections.replaceAll(StackCollection.getEntitiesCollection(), list.get(0), humanBeing);
            return new ServerResult(true);
        } catch (SQLException e) {
            System.out.println("Problem is SQL");
            return new ServerResult(false);
        }
    }
}
