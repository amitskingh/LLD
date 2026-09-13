package model;
import spot.ParkingSpot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class ParkingLot {

    private final List<Floor> floors;

    public ParkingLot(){
        this.floors = new ArrayList<>();
    }

    public void addFloor(Floor floor){
        this.floors.add(floor);
    }

    public List<Floor> getFloors(){
        return Collections.unmodifiableList(floors);
    }

    public List<ParkingSpot> getAllSpots(){
        List<ParkingSpot> allSpots = new ArrayList<>();

        for(Floor floor: floors){
            allSpots.addAll(floor.getSpots());
        }

        return Collections.unmodifiableList(allSpots);
    }

}
