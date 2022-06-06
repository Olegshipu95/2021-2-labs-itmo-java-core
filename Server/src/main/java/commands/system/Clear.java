

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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

import static commands.CommandArgs.NO_ARGS;

public class Clear extends CommandsToCollection {
    public Clear() {
        super("clear", NO_ARGS, "clear the collection");
    }

    public ServerResult function(DataForArray dataForArray) {
        Lock writeLock = new ReentrantReadWriteLock().writeLock();
        writeLock.lock();
        try {
            Connection connection = ConnectToDataBase.getConnection();
            String request = "select id from objects where login = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
             preparedStatement.setString(1, dataForArray.getName());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            Set<Integer> idObjects = new HashSet<>();
            while (resultSet.next()){
                idObjects.add(resultSet.getInt(1));
                System.out.println(resultSet.getInt(1));
            }
            request = "delete from objects where login = ?";
            PreparedStatement preparedStatement2 = connection.prepareStatement(request);
            preparedStatement2.setString(1, dataForArray.getName());
            preparedStatement2.execute();
            List<HumanBeing> list = StackCollection.getEntitiesCollection().stream().filter(x->idObjects.contains(x.getId())).collect(Collectors.toCollection(ArrayList::new));
            StackCollection.getEntitiesCollection().removeAll(list);
            return new ServerResult(true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ServerResult(false);
        }
        finally {
            writeLock.unlock();
        }
    }
}
