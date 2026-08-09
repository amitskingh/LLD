package p09_simple_factory_pattern;

class PushNotification implements Notification {
    @Override
    public void send() {
        System.out.println("Sending a push notification");
    }
    
}
