package p10_factory_metod_pattern;

public class Main {
    public static void main(String[] args) {
        NotificationCreator creator = new EmailCreator();
        creator.sendNotification();

    }

}
