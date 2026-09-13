package strategy;

import model.ParkingLot;
import model.Vehicle;
import model.VehicleType;
import spot.ParkingSpot;

public class DefaultSpotAssignmentStrategy implements SpotAssignmentStrategy{

    @Override
    public ParkingSpot findSpot(ParkingLot parkingLot, Vehicle vehicle) {

        if(VehicleType.MOTORCYCLE == vehicle.getType()){
            return findFirstSpotOfType(
                    parkingLot,
                    vehicle,
                    spot.MotorCycleSpot.class
            );
        }

        if (vehicle.getType() == VehicleType.CAR) {
            ParkingSpot carSpot = findFirstSpotOfType(
                    parkingLot,
                    vehicle,
                    spot.CarSpot.class
            );

            if (carSpot != null) {
                return carSpot;
            }

            return findFirstSpotOfType(
                    parkingLot,
                    vehicle,
                    spot.LargeSpot.class
            );
        }

        if (vehicle.getType() == VehicleType.BUS_VAN) {
            return findFirstSpotOfType(
                    parkingLot,
                    vehicle,
                    spot.LargeSpot.class
            );
        }

        return null;

    }

    private ParkingSpot findFirstSpotOfType(ParkingLot parkingLot, Vehicle vehicle, Class<? extends ParkingSpot> spotType){
        for (ParkingSpot spot : parkingLot.getAllSpots()) {

            if (spotType.isInstance(spot)
                    && spot.canPark(vehicle)) {

                return spot;
            }
        }

        return null;
    }

}
