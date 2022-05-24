package commands;//

import collections.CommandCollection;
import collections.IdCollection;
import collections.StackCollection;
import connect.ConnectToDataBase;
import entities.*;
import org.apache.commons.csv.CSVRecord;

import java.sql.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class WriteTheValues {

    public static HumanBeing createObject(DataForArray dataForArray) throws SQLException {
        Connection connection = ConnectToDataBase.getConnection();
        Mood mood = null;
        WeaponType weaponType = null;
        String[] args = dataForArray.getArgs();
        int id = Integer.parseInt(args[0]);
        String name = String.valueOf(args[1]);
        float x = Float.parseFloat(args[2]);
        int y = Integer.parseInt(args[3]);
        boolean realHero = Boolean.parseBoolean(args[4]);
        boolean hasToothpick = Boolean.parseBoolean(args[5]);
        float impactSpeed = Float.parseFloat(args[6]);
        int minutesOfWait = Integer.parseInt(args[7]);
        weaponType = WeaponType.valueOf(args[8]);
        mood = Mood.valueOf(args[9]);
        boolean cool = Boolean.parseBoolean(args[10]);

        String request = "insert into objects (id, name, x, y, realhero, hastoothpick, impactspeed, minutesofwaiting, weapontype, mood, car, login, creationdate) values (?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(request);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setFloat(3, x);
        preparedStatement.setInt(4, y);
        preparedStatement.setString(5, Boolean.toString(realHero));
        preparedStatement.setString(6, Boolean.valueOf(hasToothpick).toString());
        preparedStatement.setDouble(7, impactSpeed);
        preparedStatement.setInt(8, minutesOfWait);
        preparedStatement.setString(9, weaponType.toString());
        preparedStatement.setString(10, mood.toString());
        preparedStatement.setBoolean(11, cool);
        preparedStatement.setString(12, dataForArray.getName());
        preparedStatement.setTimestamp(13, new java.sql.Timestamp(new java.util.Date().getTime()));
        preparedStatement.execute();
        System.out.println("Tut rabotaet");
        return new HumanBeing(id, name, new Coordinates(x, y), realHero, hasToothpick, impactSpeed, minutesOfWait, weaponType, mood, new Car(cool));
    }

//    public static boolean createObject(CSVRecord arguments) {
//        Mood mood = null;
//        WeaponType weaponType = null;
//
//        String name;
//        int y;
//        int minutesOfWait;
//        float x;
//        float impactSpeed;
//        boolean realHero;
//        boolean hasToothpick;
//        boolean cool;
//        int id;
//        LocalDateTime localDateTime;
//        try {
//            id = Integer.parseInt(arguments.get(0));
//            if (IdCollection.idCollection.contains(id)) {
//                return false;
//            }
//
//            name = String.valueOf(arguments.get(1));
//            if (name == null || name.trim().isEmpty()) {
//                return false;
//            }
//
//            x = Float.parseFloat(arguments.get(2));
//            y = Integer.parseInt(arguments.get(3));
//            int year = Integer.parseInt(arguments.get(4));
//            int month = Integer.parseInt(arguments.get(5));
//            int dayOfMonth = Integer.parseInt(arguments.get(6));
//            int hour = Integer.parseInt(arguments.get(7));
//            int minute = Integer.parseInt(arguments.get(8));
//            localDateTime = LocalDateTime.of(year, month, dayOfMonth, hour, minute);
//            realHero = Boolean.parseBoolean(arguments.get(9));
//            hasToothpick = Boolean.parseBoolean(arguments.get(10));
//            impactSpeed = Float.parseFloat(arguments.get(11));
//            minutesOfWait = Integer.parseInt(arguments.get(12));
//            mood = Mood.valueOf(arguments.get(14));
//            weaponType = WeaponType.valueOf(arguments.get(13));
//            cool = Boolean.parseBoolean(arguments.get(15));
//        } catch (IllegalArgumentException | DateTimeException e) {
//            return false;
//        }
//
//        StackCollection.entitiesCollection.push(new HumanBeing(id, name, new Coordinates(x, y), localDateTime, realHero, hasToothpick, impactSpeed, minutesOfWait, weaponType, mood, new Car(cool)));
//        return true;
//    }

    public static void addObjectsFromDB(){
        try {
            Connection connection = ConnectToDataBase.getConnection();
            String request = "select id, name,creationdate, x, y, realhero, hastoothpick, impactspeed, minutesofwaiting, weapontype, mood, car from objects";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDateTime localDateTime = resultSet.getTimestamp("creationdate").toLocalDateTime();
                float x = resultSet.getFloat("x");
                int y = resultSet.getInt("y");
                boolean realHero = resultSet.getBoolean("realhero");
                boolean hasToothpick = resultSet.getBoolean("hastoothpick");
                float impactSpeed = resultSet.getFloat("impactspeed");
                int minutesOfWait = resultSet.getInt("minutesofwaiting");
                WeaponType weaponType = WeaponType.valueOf(resultSet.getString("weapontype"));
                Mood mood = Mood.valueOf(resultSet.getString("mood"));
                boolean cool = resultSet.getBoolean("car");
                StackCollection.getEntitiesCollection().push(new HumanBeing(id,name,new Coordinates(x,y),localDateTime,realHero,
                        hasToothpick,impactSpeed,minutesOfWait,weaponType,mood,new Car(cool)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static HumanBeing updateObject(String[] args) throws SQLException{
            Connection connection = ConnectToDataBase.getConnection();
            String request = "update objects set name = ?,x = ?, y = ?, realhero = ?, hastoothpick = ?, impactspeed = ?, minutesofwaiting = ?, weapontype = ?, mood = ?, car = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(request);
            preparedStatement.setInt(11,Integer.parseInt(args[0]));
            preparedStatement.setString(1, args[1]);
            preparedStatement.setFloat(2, Float.parseFloat(args[2]));
            preparedStatement.setInt(3, Integer.parseInt(args[3]));
            preparedStatement.setString(4, args[4]);
            preparedStatement.setString(5, args[5]);
            preparedStatement.setFloat(6, Float.parseFloat(args[6]));
            preparedStatement.setInt(7, Integer.parseInt(args[7]));
            preparedStatement.setString(8, args[8]);
            preparedStatement.setString(9, args[9]);
            preparedStatement.setBoolean(10, Boolean.parseBoolean(args[10]));
            preparedStatement.execute();
            return new HumanBeing(Integer.parseInt(args[0]),args[1],new Coordinates(Float.parseFloat(args[2]),Integer.parseInt(args[3])),
                    Boolean.parseBoolean(args[4]),Boolean.parseBoolean(args[5]),Float.parseFloat(args[6]),Integer.parseInt(args[7]),
                    WeaponType.valueOf(args[8]),Mood.valueOf(args[9]),new Car(Boolean.parseBoolean(args[10])));
    }
}
