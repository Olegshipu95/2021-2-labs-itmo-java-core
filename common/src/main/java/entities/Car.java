
package entities;

public class Car {
    private Boolean cool;

    public Car(boolean a) {
        this.cool = a;
    }

    public String toString() {
        return "Car{cool=" + this.cool + "}";
    }

    public Boolean getCool() {
        return this.cool;
    }
}
