package p10_factory_metod_pattern;

class PushNotification implements Notification {
    @Override
    public void send() {
        System.out.println("Sending a push notification");
    }
    
}
