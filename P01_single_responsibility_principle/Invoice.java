package P01_single_responsibility_principle;

class Invoice {
    private double amount;

    Invoice(double amount) {
        this.amount = amount;
    }

    double calculateTotal() {
        return amount;
    }

}
