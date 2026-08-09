package p09_simple_factory_pattern;

public class Main {

    public static void main(String[] args) {
        Notification notification = NotificationFactory.create(NotificationType.SMS);

        notification.send();
    }

}
