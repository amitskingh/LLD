package strategy;

import model.ParkingTicket;

import java.time.Duration;
import java.time.LocalDateTime;

public class DefaultPricingStrategy implements PricingStrategy {

    private static final double BASE_PRICE = 100.0;
    private static final double HOURLY_PRICE = 50.0;
    private static final long BASE_HOURS = 2;

    @Override
    public double calculatePrice(
            ParkingTicket ticket,
            LocalDateTime exitTime) {

        Duration duration = Duration.between(
                ticket.getEntryTime(),
                exitTime
        );

        long totalMinutes = duration.toMinutes();

        if (totalMinutes <= BASE_HOURS * 60) {
            return BASE_PRICE;
        }

        long extraMinutes =
                totalMinutes - (BASE_HOURS * 60);

        long additionalHours =
                (extraMinutes + 59) / 60;

        return BASE_PRICE
                + additionalHours * HOURLY_PRICE;
    }
}
