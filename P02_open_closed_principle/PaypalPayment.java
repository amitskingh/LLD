package P02_open_closed_principle;

class PaypalPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {

        System.out.println("Paid Rs." + amount + " using PayPal");

    }

}
