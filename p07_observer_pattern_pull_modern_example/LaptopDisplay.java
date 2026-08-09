package p07_observer_pattern_pull_modern_example;

class LaptopDisplay implements Observer {

    @Override
    public void update(Subject subject) {

        WeatherStation station = (WeatherStation) subject;

        System.out.println(
                "Laptop : Humidity = " +
                        station.getHumidity());

    }

}