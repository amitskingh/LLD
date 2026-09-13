package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import spot.ParkingSpot;

public class Floor {

    private final int floorId;
    private final List<ParkingSpot> spots;

    public Floor(int floorId) {
        this.floorId = floorId;
        this.spots = new ArrayList<>();
    }

    public void addSpot(ParkingSpot spot) {
        spots.add(spot);
    }

    public int getFloorId() {
        return floorId;
    }

    public List<ParkingSpot> getSpots() {
        return Collections.unmodifiableList(spots);
    }

}
