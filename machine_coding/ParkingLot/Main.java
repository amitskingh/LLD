
import model.Floor;
import model.ParkingLot;
import model.ParkingTicket;
import model.Vehicle;
import model.VehicleType;
import payment.CardPayment;
import payment.Payment;
import payment.PaymentMethod;
import service.ParkingService;
import spot.CarSpot;
import spot.LargeSpot;
import spot.MotorCycleSpot;
import strategy.DefaultPricingStrategy;
import strategy.DefaultSpotAssignmentStrategy;
import strategy.PricingStrategy;
import strategy.SpotAssignmentStrategy;

public class Main {

    public static void main(String[] args) {

        // -------------------------
        // 1. Create parking lot
        // -------------------------
        ParkingLot parkingLot = new ParkingLot();

        Floor floor1 = new Floor(1);

        floor1.addSpot(new MotorCycleSpot("M1"));
        floor1.addSpot(new CarSpot("C1"));
        floor1.addSpot(new LargeSpot("L1"));

        parkingLot.addFloor(floor1);

        // -------------------------
        // 2. Create strategies
        // -------------------------
        SpotAssignmentStrategy assignmentStrategy
                = new DefaultSpotAssignmentStrategy();

        PricingStrategy pricingStrategy
                = new DefaultPricingStrategy();

        // -------------------------
        // 3. Create parking service
        // -------------------------
        ParkingService parkingService
                = new ParkingService(
                        parkingLot,
                        assignmentStrategy,
                        pricingStrategy
                );

        // -------------------------
        // 4. Vehicle arrives
        // -------------------------
        Vehicle car
                = new Vehicle(
                        "MH12AB1234",
                        VehicleType.CAR
                );

        // -------------------------
        // 5. Park vehicle
        // -------------------------
        ParkingTicket ticket
                = parkingService.parkVehicle(car);

        System.out.println(
                "Ticket ID: "
                + ticket.getTicketId()
        );

        System.out.println(
                "Spot: "
                + ticket.getParkingSpot().getSpotId()
        );

        // -------------------------
        // 6. Vehicle exits
        // -------------------------
        PaymentMethod paymentMethod
                = new CardPayment();

        Payment payment
                = parkingService.exitVehicle(
                        car.getRegistrationNumber(),
                        paymentMethod
                );

        // -------------------------
        // 7. Payment result
        // -------------------------
        System.out.println(
                "Amount paid: ₹"
                + payment.getAmount()
        );

        System.out.println(
                "Payment status: "
                + payment.getStatus()
        );

        System.out.println(
                "Ticket status: "
                + ticket.getStatus()
        );
    }
}
