package p09_simple_factory_pattern;

class EmailNotification implements Notification {
    @Override
    public void send() {
        System.out.println("Sending an email notification");
    }
    
}
