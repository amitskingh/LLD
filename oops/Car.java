package oops;

public class Car extends Vehicle {
    private String brand;
    int speed;

    Car(int speed) {
        this.speed = speed;
    }

    void setBrand(String brand) {
        this.brand = brand;
    }

    String getBrand() {
        return this.brand;
    }

    void drive() {
        System.out.println("The " + brand + " is driving at " + speed + " km/h.");
    }

    @Override
    void start() {
        System.out.println("Car is starting");
    }
}
