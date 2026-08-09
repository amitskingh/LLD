package p10_factory_metod_pattern;

public class NotificationFactory {

    static Notification create(NotificationType type) {
        switch (type) {
            case SMS:
                return new SMSNotification();
            case EMAIL:
                return new EmailNotification();
            case PUSH:
                return new PushNotification();
            default:
                throw new IllegalArgumentException("Unknown notification type");
        }
    }

}
