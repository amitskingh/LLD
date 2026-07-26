package P06_strategy_design_pattern;

class UpiPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {

        System.out.println("Paid ₹" + amount + " using UPI");

    }

}
