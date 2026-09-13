package payment;

public class CashPayment implements PaymentMethod {

    @Override
    public boolean pay(double amount) {
        System.out.println("Payment received through Cash: ₹" + amount);
        return true;
    }
}
