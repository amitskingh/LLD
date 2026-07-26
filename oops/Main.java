package oops;

public class Main {
    public static void main(String[] args) {
        Car car = new Car(60);
        car.setBrand("Toyota");
        car.drive();
        String brand = car.getBrand();
        System.out.println("The car brand is: " + brand);

        Animal animal = new Dog();
        animal.sound();

        Payment payment = new UpiPayment();
        payment.pay(100.0);
    }
}
