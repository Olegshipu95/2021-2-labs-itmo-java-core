
package commands.system;

import collections.StackCollection;
import commands.*;
import connect.ConnectToDataBase;
import entities.HumanBeing;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RemoveAt extends CommandsToCollection {
    public RemoveAt() {
        super("remove_at", CommandArgs.ID_ARGS, "delete an item located in the specified position of the collection (index)");
    }

    public ServerResult function(DataForArray dataForArray) {
        try {
            int id = Integer.parseInt(dataForArray.getArgs()[0]);
            StackCollection.entitiesCollection.get(id+1);
            Connection connection = ConnectToDataBase.getConnection();
            String request = "delete from objects where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setInt(1, Integer.parseInt(dataForArray.getArgs()[0]));
            preparedStatement.execute();
            List<HumanBeing> list = StackCollection.getEntitiesCollection().stream().filter(x -> x.getId() == id).collect(Collectors.toCollection(ArrayList::new));
            StackCollection.getEntitiesCollection().removeAll(list);
            return new ServerResult(true);
        } catch (SQLException e) {
            e.printStackTrace();
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("Some problems with SQL");
            return new ServerResult(arrayList, false);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Vne massiva");
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("Vne massiva");
            return new ServerResult(arrayList, false);
        }
    }
}
