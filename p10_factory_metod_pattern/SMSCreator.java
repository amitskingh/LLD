package p10_factory_metod_pattern;

class SMSCreator extends NotificationCreator {

    @Override
    Notification createNotification(){
        return new SMSNotification();
    }
    
}
