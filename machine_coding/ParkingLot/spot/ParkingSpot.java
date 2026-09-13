package spot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Vehicle;

public abstract class ParkingSpot {

    public final String spotId;
    private final List<Vehicle> vehicles;

    protected ParkingSpot(String spotId){
        this.spotId = spotId;
        this.vehicles = new ArrayList<>();
    }

    public abstract boolean canPark(Vehicle vehicle);

    public void park(Vehicle vehicle){
        if(!canPark(vehicle)){
            throw new IllegalStateException(
                    "Vehicle cannot park in spot " + spotId
            );
        }

        vehicles.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle){
        vehicles.remove(vehicle);
    }

    public String getSpotId(){
        return spotId;
    }


    public List<Vehicle> getVehicles(){
        return Collections.unmodifiableList(vehicles);
    }

}
