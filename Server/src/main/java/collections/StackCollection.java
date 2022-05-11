package collections;

import entities.*;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.Stack;

/**
 * This class makes a stack collection with {@link HumanBeing#HumanBeing(int, String, Coordinates, LocalDateTime, boolean, boolean, Float, Integer, WeaponType, Mood, Car)} objects
 */
public class StackCollection {
    /**
     * It is stack collection with {@link HumanBeing#HumanBeing(int, String, Coordinates, LocalDateTime, boolean, boolean, Float, Integer, WeaponType, Mood, Car)} objects
     */
    @Getter
    public static Stack<HumanBeing> entitiesCollection = new Stack();
}