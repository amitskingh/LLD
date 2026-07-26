package P07_observer_pattern_push_example;

import java.util.*;

class WeatherStation implements WeatherSubject {

    private List<WeatherObserver> observers = new ArrayList<>();

    private int temperature;
    private int humidity;


    @Override
    public void attach(WeatherObserver observer) {
        observers.add(observer);
    }


    @Override
    public void detach(WeatherObserver observer) {
        observers.remove(observer);
    }


    @Override
    public void notifyObservers(){
        for(WeatherObserver observer: observers){
            observer.update(temperature, humidity);
        }
    }


    public void setWeather(int temperature, int humidity){
        this.temperature = temperature;
        this.humidity = humidity;

        notifyObservers();
    }
    
}
