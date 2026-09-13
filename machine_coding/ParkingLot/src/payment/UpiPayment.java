package payment;

public class UpiPayment implements PaymentMethod {

    @Override
    public boolean pay(double amount) {
        System.out.println("Payment received through UPI: ₹" + amount);
        return true;
    }
}
