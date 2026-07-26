package P07_observer_pattern_push_example;

class LaptopDisplay implements WeatherObserver {

    @Override
    public void update(int temperature, int humidity) {

        System.out.println(
            "Laptop : Temp = " + temperature +
            ", Humidity = " + humidity
        );

    }
}