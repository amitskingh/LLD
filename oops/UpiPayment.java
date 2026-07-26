package oops;

interface Payment {
    void pay(double amount);
}

public class UpiPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using UPI.");
    }

}
