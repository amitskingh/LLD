package P07_observer_pattern_pull_modern_example;

interface Subject {

    void attach(Observer observer);

    void detach(Observer observer);

    void notifyObservers();

}