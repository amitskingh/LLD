package P07_observer_pattern_pull_example;

class LaptopDisplay implements Observer {

    private WeatherStation station;

    public LaptopDisplay(WeatherStation station) {
        this.station = station;
    }

    @Override
    public void update() {

        int temp = station.getTemperature();
        int humidity = station.getHumidity();

        System.out.println(
            "Laptop : Temp = " + temp +
            ", Humidity = " + humidity
        );

    }

}