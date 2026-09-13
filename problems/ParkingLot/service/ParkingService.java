package service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import model.ParkingLot;
import model.ParkingTicket;
import model.Vehicle;
import payment.Payment;
import payment.PaymentMethod;
import payment.PaymentStatus;
import spot.ParkingSpot;
import strategy.PricingStrategy;
import strategy.SpotAssignmentStrategy;

public class ParkingService {

    private final ParkingLot parkingLot;
    private final SpotAssignmentStrategy assignmentStrategy;

    private final Map<String, ParkingTicket> activeTickets;

    private final PricingStrategy pricingStrategy;

    public ParkingService(
            ParkingLot parkingLot,
            SpotAssignmentStrategy assignmentStrategy,
            PricingStrategy pricingStrategy
    ) {

        this.parkingLot = parkingLot;
        this.assignmentStrategy = assignmentStrategy;
        this.pricingStrategy = pricingStrategy;
        this.activeTickets = new HashMap<>();
    }

    public ParkingTicket parkVehicle(Vehicle vehicle) {

        String registrationNumber
                = vehicle.getRegistrationNumber();

        // 1. Vehicle already parked?
        if (activeTickets.containsKey(registrationNumber)) {
            throw new IllegalStateException(
                    "Vehicle is already parked"
            );
        }

        // 2. Find suitable spot
        ParkingSpot spot
                = assignmentStrategy.findSpot(
                        parkingLot,
                        vehicle
                );

        // 3. No suitable spot
        if (spot == null) {
            throw new IllegalStateException(
                    "No suitable parking spot available"
            );
        }

        // 4. Actually park the vehicle
        spot.park(vehicle);

        // 5. Create ticket
        ParkingTicket ticket
                = new ParkingTicket(
                        UUID.randomUUID().toString(),
                        vehicle,
                        spot,
                        LocalDateTime.now()
                );

        // 6. Track active session
        activeTickets.put(
                registrationNumber,
                ticket
        );

        return ticket;
    }

    public Payment exitVehicle(
            String registrationNumber,
            PaymentMethod paymentMethod) {

        // 1. Find active ticket
        ParkingTicket ticket
                = activeTickets.get(registrationNumber);

        if (ticket == null) {
            throw new IllegalStateException(
                    "Vehicle is not currently parked"
            );
        }

        // 2. Determine exit time
        LocalDateTime exitTime = LocalDateTime.now();

        // 3. Calculate parking fee
        double amount
                = pricingStrategy.calculatePrice(
                        ticket,
                        exitTime
                );

        // 4. Create payment
        Payment payment
                = new Payment(
                        amount,
                        paymentMethod
                );

        // 5. Process payment
        payment.process();

        // 6. Payment failed → do NOTHING else
        if (payment.getStatus() == PaymentStatus.FAILED) {
            throw new IllegalStateException(
                    "Payment failed. Vehicle remains parked."
            );
        }

        // 7. Payment succeeded
        ticket.markPaid();

        // 8. Release parking spot
        ticket.getParkingSpot()
                .removeVehicle(ticket.getVehicle());

        // 9. Complete ticket
        ticket.complete(exitTime);

        // 10. Remove active parking session
        activeTickets.remove(registrationNumber);
        return payment;
    }
}
