package P07_observer_pattern_pull_example;

public class Main {

    public static void main(String[] args) {

        WeatherStation station = new WeatherStation();

        station.attach(new MobileDisplay(station));
        station.attach(new LaptopDisplay(station));

        station.setWeather(35, 80);

    }

}