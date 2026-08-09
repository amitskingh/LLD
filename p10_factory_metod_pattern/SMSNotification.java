package p10_factory_metod_pattern;

class SMSNotification implements Notification {
    @Override
    public void send() {
        System.out.println("Sending an SMS notification");
    }
    
}
