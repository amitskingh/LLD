package P07_observer_pattern_push_example;

/**
 * WeatherObserver
 */
interface WeatherObserver {

    void update(int temperature, int humidity);
    
}
