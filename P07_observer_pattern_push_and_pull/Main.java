/**
 * Main
 */
package P07_observer_pattern_push_and_pull;

public class Main {

    public static void main(String[] args) {

        YouTubeChannel channel = new YouTubeChannel();

        VideoObserver alice = new Subscriber("Alice");
        VideoObserver bob = new Subscriber("Bob");
        VideoObserver charlie = new Subscriber("Charlie");


        channel.subscribe(alice);
        channel.subscribe(bob);
        channel.subscribe(charlie);


        channel.uploadVideo("Observer Pattern");

    }

}
