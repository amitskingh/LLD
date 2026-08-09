package p02_open_closed_principle;

public class Main {

    public static void main(String[] args) {

        PaymentService cardPaymentService = new PaymentService(new CreditCardPayment());
        cardPaymentService.processPayment(1000.0);

        PaymentService upiPaymentService = new PaymentService(new UpiPayment());
        upiPaymentService.processPayment(500.0);

        PaymentService paypalPaymentService = new PaymentService(new PaypalPayment());
        paypalPaymentService.processPayment(750.0);

        PaymentService walletPaymentService = new PaymentService(new WalletPayment());
        walletPaymentService.processPayment(250.0);

    }

}
