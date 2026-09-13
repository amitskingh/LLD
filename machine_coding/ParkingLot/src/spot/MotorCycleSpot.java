package spot;

import model.Vehicle;
import model.VehicleType;

public class MotorCycleSpot extends ParkingSpot{

    public MotorCycleSpot(String spotId){
        super(spotId);
    }

    @Override
    public boolean canPark(Vehicle vehicle){
        return VehicleType.MOTORCYCLE == vehicle.getType() && getVehicles().isEmpty();
    }

}
