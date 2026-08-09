package p10_factory_metod_pattern;

abstract class NotificationCreator {

    // Factory Method
    abstract Notification createNotification();

    // common method to send notification
    public void sendNotification() {
        // call the factory method to create a Notification object
        Notification notification = createNotification();
        // send the notification
        notification.send();
    }


}
