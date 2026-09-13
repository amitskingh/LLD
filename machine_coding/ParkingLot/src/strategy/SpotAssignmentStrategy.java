package strategy;


import model.ParkingLot;
import model.Vehicle;
import spot.ParkingSpot;

public interface SpotAssignmentStrategy {

    ParkingSpot findSpot(ParkingLot parkingLot, Vehicle vehicle);

}
