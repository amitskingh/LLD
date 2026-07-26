package P02_open_closed_principle;

class WalletPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {

        System.out.println("Paid Rs." + amount + " using Wallet");

    }

}
