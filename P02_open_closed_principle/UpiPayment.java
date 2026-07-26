package P02_open_closed_principle;

class UpiPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {

        System.out.println("Paid Rs." + amount + " using UPI");

    }

}
