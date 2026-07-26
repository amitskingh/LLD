package P06_strategy_design_pattern;

public class Main {
    public static void main(String[] args) {
        PaymentService creditCardPaymentService = new PaymentService(new CreditCardPayment());
        creditCardPaymentService.processPayment(1000.0);

        PaymentService upiPaymentService = new PaymentService(new UpiPayment());
        upiPaymentService.processPayment(500.0);
    }
}
