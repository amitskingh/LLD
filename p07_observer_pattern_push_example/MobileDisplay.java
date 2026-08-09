package p07_observer_pattern_push_example;

public class MobileDisplay implements WeatherObserver{
   
    @Override
    public void update(int temperature, int humidity){
        System.out.println(
            "Mobile: Temp = " + temperature + 
            ", Humidity = " + humidity
        );
    }
    
}
