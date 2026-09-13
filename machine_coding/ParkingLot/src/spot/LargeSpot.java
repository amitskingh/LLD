package spot;

import model.Vehicle;
import model.VehicleType;

public class LargeSpot extends ParkingSpot{
    private static final int MAX_CARS = 2;

    public LargeSpot(String spotId){
        super(spotId);
    }

    @Override
    public boolean canPark(Vehicle vehicle){

        if(VehicleType.BUS_VAN == vehicle.getType()){
            return getVehicles().isEmpty();
        }

        if(VehicleType.CAR == vehicle.getType()){
            return getVehicles().stream().allMatch(v -> v.getType() == VehicleType.CAR) && getVehicles().size() < MAX_CARS;
        }

        return  false;
    }

}
