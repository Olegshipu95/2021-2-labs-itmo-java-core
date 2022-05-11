package entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Coordinates {
    @NonNull
    @Getter
    private Float x; //Поле не может быть null
    @NonNull
    @Getter

    private Integer y; //Максимальное значение поля: 475, Поле не может быть null

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
