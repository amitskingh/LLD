package p07_observer_pattern_push_example;

interface WeatherSubject {

    void attach(WeatherObserver observer);

    void detach(WeatherObserver observer);

    void notifyObservers();

}
