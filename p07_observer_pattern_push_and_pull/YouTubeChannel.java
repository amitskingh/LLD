package p07_observer_pattern_push_and_pull;

import java.util.ArrayList;
import java.util.List;


/**
 * YouTubeChannel
 */
class YouTubeChannel implements ChannelSubject{

    private List<VideoObserver> subscribers = new ArrayList<>();

    private String lastestVideo;


    @Override
    public void subscribe(VideoObserver observer){
        subscribers.add(observer);
    }


    @Override
    public void unSubscribe(VideoObserver observer){
        subscribers.remove(observer);
    }


    @Override
    public void notifySubscribers(){

        for(VideoObserver observer : subscribers){
            observer.update(lastestVideo);
        }
        
    }


    void uploadVideo(String title){
        lastestVideo = title;
        notifySubscribers();
    }
    
}
