package commands;//

import collections.IdCollection;
import collections.StackCollection;
import connect.ConnectToDataBase;
import entities.*;
import org.apache.commons.csv.CSVRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDateTime;

public class WriteTheValues {

    public static HumanBeing createObject(String[] args) throws SQLException {
        Connection connection = ConnectToDataBase.getConnection();
        Mood mood = null;
        WeaponType weaponType = null;
        int id = Integer.valueOf(args[0]);
        String name = String.valueOf(args[1]);
        Float x = Float.valueOf(args[2]);
        Integer y = Integer.valueOf(args[3]);
        boolean realHero = Boolean.valueOf(args[4]);
        boolean hasToothpick = Boolean.valueOf(args[5]);
        Float impactSpeed = Float.valueOf(args[6]);
        Integer minutesOfWait = Integer.valueOf(args[7]);
        weaponType = WeaponType.valueOf(args[8]);
        mood = Mood.valueOf(args[9]);
        boolean cool = Boolean.valueOf(args[10]);
        String request = "INSERT INTO \"humanBeing  \" VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(request);
        preparedStatement.setString(1, args[0]);
        preparedStatement.setString(2, args[1]);
        preparedStatement.setString(3, LocalDateTime.now().toString());
        preparedStatement.setString(4, args[2]);
        preparedStatement.setString(5, args[3]);
        preparedStatement.setString(6, args[4]);
        preparedStatement.setString(7, args[5]);
        preparedStatement.setString(8, args[6]);
        preparedStatement.setString(9, args[7]);
        preparedStatement.setString(10, args[8]);
        preparedStatement.setString(11,args[9]);
        preparedStatement.setString(12,args[10]);
        preparedStatement.execute();
        return new HumanBeing(id, name, new Coordinates(x, y), realHero, hasToothpick, impactSpeed, minutesOfWait, weaponType, mood, new Car(cool));
//            StackCollection.entitiesCollection.push();
//            return true;
    }

    public static boolean createObject(CSVRecord arguments) {
        Mood mood = null;
        WeaponType weaponType = null;

        String name;
        Integer y;
        Integer minutesOfWait;
        Float x;
        Float impactSpeed;
        boolean realHero;
        boolean hasToothpick;
        boolean cool;
        int id;
        LocalDateTime localDateTime;
        try {
            id = Integer.valueOf(arguments.get(0));
            if (IdCollection.idCollection.contains(id)) {
                return false;
            }

            name = String.valueOf(arguments.get(1));
            if (name == null || name.trim().isEmpty()) {
                return false;
            }

            x = Float.valueOf(arguments.get(2));
            y = Integer.valueOf(arguments.get(3));
            int year = Integer.valueOf(arguments.get(4));
            int month = Integer.valueOf(arguments.get(5));
            int dayOfMonth = Integer.valueOf(arguments.get(6));
            int hour = Integer.valueOf(arguments.get(7));
            int minute = Integer.valueOf(arguments.get(8));
            localDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
            realHero = Boolean.valueOf(arguments.get(9));
            hasToothpick = Boolean.valueOf(arguments.get(10));
            impactSpeed = Float.valueOf(arguments.get(11));
            minutesOfWait = Integer.valueOf(arguments.get(12));
            mood = Mood.valueOf(arguments.get(14));
            weaponType = WeaponType.valueOf(arguments.get(13));
            cool = Boolean.valueOf(arguments.get(15));
        } catch (IllegalArgumentException e) {
            return false;
        } catch (DateTimeException e) {
            return false;
        }

        StackCollection.entitiesCollection.push(new HumanBeing(id, name, new Coordinates(x, y), localDateTime, realHero, hasToothpick, impactSpeed, minutesOfWait, weaponType, mood, new Car(cool)));
        return true;
    }
}
