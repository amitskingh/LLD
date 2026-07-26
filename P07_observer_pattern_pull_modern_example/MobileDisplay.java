package P07_observer_pattern_pull_modern_example;

class MobileDisplay implements Observer {

    @Override
    public void update(Subject subject) {

        WeatherStation station = (WeatherStation) subject;

        System.out.println(
                "Mobile : Temp = " +
                        station.getTemperature());

    }

}