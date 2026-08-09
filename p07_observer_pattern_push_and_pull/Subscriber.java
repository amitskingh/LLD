/**
 * Subscriber
 */
package p07_observer_pattern_push_and_pull;

class Subscriber implements VideoObserver {

    private String name;

    Subscriber(String name){
        this.name = name;
    }

    
    @Override
    public void update(String videoTitle){
        System.out.println(
            name + " received notifications: " + videoTitle
        );
    }
    
}
