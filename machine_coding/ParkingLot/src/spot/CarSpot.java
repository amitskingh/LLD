package spot;

import model.Vehicle;
import model.VehicleType;

public class CarSpot extends ParkingSpot{

    public CarSpot(String spotId){
        super(spotId);
    }

    @Override
    public boolean canPark(Vehicle vehicle){
       return VehicleType.CAR == vehicle.getType() && getVehicles().isEmpty();
    }

}
