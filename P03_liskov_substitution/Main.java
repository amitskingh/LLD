package P03_liskov_substitution;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(new Car());
        vehicleList.add(new MotorCycle());

        for (Vehicle vehicle : vehicleList) {
            System.out.println(vehicle.hasEngine().toString());
        }
    }
}
