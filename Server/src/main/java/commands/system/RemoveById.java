

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
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class RemoveById extends CommandsToCollection {
    public RemoveById() {
        super("remove_by_id", CommandArgs.ID_ARGS, "delete an item from the collection by its id");
    }

    public ServerResult function(DataForArray dataForArray) {
        Connection connection = ConnectToDataBase.getConnection();
        try {
            String request = "delete from objects where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setInt(1,Integer.parseInt(dataForArray.getArgs()[0]));
            preparedStatement.execute();
            int id = Integer.parseInt(dataForArray.getArgs()[0]);
            List<HumanBeing> list = StackCollection.getEntitiesCollection().stream().filter(x->x.getId()==id).collect(Collectors.toCollection(ArrayList::new));
            StackCollection.getEntitiesCollection().removeAll(list);
            return new ServerResult(true);
        } catch (SQLException e) {
            e.printStackTrace();
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("Some problems with SQL");
            return new ServerResult(arrayList,false);
        }
    }
}
