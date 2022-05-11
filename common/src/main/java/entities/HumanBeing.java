package entities;

import collections.IdCollection;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode(of = {"id" , "name"})
public class HumanBeing {
    public HumanBeing(int id,String name, Coordinates coordinates, boolean realHero,
                      boolean hasToothpick, Float impactSpeed, Integer minutesOfWaiting,WeaponType weaponType, Mood mood, Car car){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.minutesOfWaiting = minutesOfWaiting;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
        IdCollection.idCollection.add(id);
        creationDate = LocalDateTime.now();
    }
    public HumanBeing(int id,String name, Coordinates coordinates, LocalDateTime local, boolean realHero,
                      boolean hasToothpick, Float impactSpeed, Integer minutesOfWaiting,WeaponType weaponType, Mood mood, Car car){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.minutesOfWaiting = minutesOfWaiting;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
        IdCollection.idCollection.add(id);
        creationDate = local;
    }
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private boolean realHero;
    private boolean hasToothpick;
    private Float impactSpeed; //Поле может быть null
    private Integer minutesOfWaiting; //Поле может быть null
    private WeaponType weaponType; //Поле может быть null
    private Mood mood; //Поле может быть null
    private Car car; //Поле не может быть null


    public String csvToString() {
        return id +
                "," + name + "," +
                + coordinates.getX() +
                ","+ coordinates.getY() +"," + realHero +
                "," + hasToothpick +
                "," + impactSpeed +
                "," + minutesOfWaiting +
                "," + weaponType +
                "," + mood +
                "," + car.getCool();
    }

    @Override
    public String toString() {
        return "HumanBeing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", realHero=" + realHero +
                ", hasToothpick=" + hasToothpick +
                ", impactSpeed=" + impactSpeed +
                ", minutesOfWaiting=" + minutesOfWaiting +
                ", weaponType=" + weaponType +
                ", mood=" + mood +
                ", car=" + car +
                '}';
    }
}
