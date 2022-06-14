

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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class RemoveLower extends CommandsToCollection {
    public RemoveLower() {
        super("remove_lower", CommandArgs.ID_ARGS, "remove all items smaller than the specified one from the collection");
    }


    public ServerResult function(DataForArray dataForArray) {
        Connection connection = ConnectToDataBase.getConnection();
        Lock writeLock = new ReentrantReadWriteLock().writeLock();
        writeLock.lock();
        try {
            HashSet<Integer> hashSet = new HashSet<>();
            String request = "select id from objects where id < ? and login = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setInt(1,Integer.parseInt(dataForArray.getArgs()[0]));
            preparedStatement.setString(2, dataForArray.getName());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next()){
                hashSet.add(resultSet.getInt(1));
            }
            request = "delete from objects where id < ? and login = ?";
            preparedStatement = connection.prepareStatement(request);
            preparedStatement.setInt(1,Integer.parseInt(dataForArray.getArgs()[0]));
            preparedStatement.execute();
            int id = Integer.parseInt(dataForArray.getArgs()[0]);
            List<HumanBeing> list = StackCollection.getEntitiesCollection().stream().filter(x->hashSet.contains(x.getId())).collect(Collectors.toCollection(ArrayList::new));
            StackCollection.getEntitiesCollection().removeAll(list);
            return new ServerResult(true);
        } catch (SQLException e) {
            e.printStackTrace();
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("Some problems with SQL");
            return new ServerResult(arrayList,false);
        }
        finally {
            writeLock.unlock();
        }
    }
}
