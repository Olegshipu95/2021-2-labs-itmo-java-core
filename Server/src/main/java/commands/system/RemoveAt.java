
package commands.system;

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
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

public class RemoveAt extends CommandsToCollection {
    public RemoveAt() {
        super("remove_at", CommandArgs.ID_ARGS, "delete an item located in the specified position of the collection (index)");
    }

    public ServerResult function(DataForArray dataForArray) {
        Lock writeLock = new ReentrantReadWriteLock().writeLock();
        writeLock.lock();
        try {
            HashSet<Integer> hashSet = new HashSet<>();
            int id = Integer.parseInt(dataForArray.getArgs()[0]);
            int idd = StackCollection.entitiesCollection.get(id+1).getId();
            Connection connection = ConnectToDataBase.getConnection();
            String request = "select id from objects where id = ? and login = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setInt(1,idd);
            preparedStatement.setString(2, dataForArray.getName());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if(!resultSet.next()){
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add("You didn't create this object");
                return new ServerResult(arrayList,false);
            }
            preparedStatement.execute();
            resultSet = preparedStatement.getResultSet();
            while(resultSet.next()){
                hashSet.add(resultSet.getInt(1));
            }
            request = "delete from objects where id = ?";
            preparedStatement = connection.prepareStatement(request);
            preparedStatement.setInt(1, idd);
            preparedStatement.execute();
            List<HumanBeing> list = StackCollection.getEntitiesCollection().stream().filter(x ->hashSet.contains(x.getId())).collect(Collectors.toCollection(ArrayList::new));
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
        finally {
            writeLock.unlock();
        }
    }
}
