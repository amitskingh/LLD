package P06_strategy_design_pattern;

class CreditCardPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {

        System.out.println("Paid ₹" + amount + " using Credit Card");

    }

}
