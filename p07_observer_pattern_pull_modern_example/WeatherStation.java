package p07_observer_pattern_pull_modern_example;

import java.util.ArrayList;
import java.util.List;

class WeatherStation implements Subject {

    private List<Observer> observers = new ArrayList<>();

    private int temperature;
    private int humidity;

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {

        for (Observer observer : observers) {
            observer.update(this);      // <-- Pass itself
        }

    }

    public void setWeather(int temperature, int humidity) {

        this.temperature = temperature;
        this.humidity = humidity;

        notifyObservers();

    }

    public int getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

}