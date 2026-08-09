package p03_liskov_substitution;

public class Car extends Vehicle {

    @Override
    public Integer getNumberofWheels() {
        return 4;
    }
}
