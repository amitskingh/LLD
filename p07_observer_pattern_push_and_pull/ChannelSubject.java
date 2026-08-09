/**
 * ChannelSubject
 */
package p07_observer_pattern_push_and_pull;

interface ChannelSubject {

    void subscribe(VideoObserver observer);

    void unSubscribe(VideoObserver observer);

    void notifySubscribers();

}
