package p07_observer_pattern_pull_modern_example;

public class Main {

    public static void main(String[] args) {

        WeatherStation station = new WeatherStation();

        station.attach(new MobileDisplay());
        station.attach(new LaptopDisplay());

        station.setWeather(35, 80);

    }

}