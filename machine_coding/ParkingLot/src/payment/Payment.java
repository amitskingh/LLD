package payment;

import java.time.LocalDateTime;

public class Payment {

    private final double amount;
    private final PaymentMethod paymentMethod;
    private final LocalDateTime timestamp;

    private PaymentStatus status;

    public Payment(
            double amount,
            PaymentMethod paymentMethod) {

        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.timestamp = LocalDateTime.now();
    }

    public void process() {
        boolean successful = paymentMethod.pay(amount);

        status = successful
                ? PaymentStatus.SUCCESS
                : PaymentStatus.FAILED;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}
