package payment;

public class CardPayment implements PaymentMethod {

    @Override
    public boolean pay(double amount) {
        System.out.println("Payment received through Card: ₹" + amount);
        return true;
    }
}
