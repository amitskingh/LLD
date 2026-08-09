package p09_simple_factory_pattern;

class SMSNotification implements Notification {
    @Override
    public void send() {
        System.out.println("Sending an SMS notification");
    }
    
}
