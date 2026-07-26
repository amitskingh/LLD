package P07_observer_pattern_pull_example;

public class MobileDisplay implements Observer {
    
    private WeatherStation station;
    
    public MobileDisplay(WeatherStation station){
        this.station = station;
    }

    @Override
    public void update(){
        int temp = station.getTemperature();
        int humidity = station.getHumidity();


        System.out.println(
            "Mobile : Temp = " + temp +
            ", Humidity = " + humidity
        );

    }
}
