package p10_factory_metod_pattern;

class EmailCreator extends NotificationCreator {

    @Override
    Notification createNotification(){
        return new EmailNotification();
    }

}
