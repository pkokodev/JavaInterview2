package designpatterns;
/* Factory method design pattern in Java
Ex.
interface Notification --- implements Push SMS Email types
interface Payment      --- implements NetBanking UPI Card


It is a creational design pattern that talks about the creation of an object. The factory design pattern says that define an interface
 ( A java interface or an abstract class) for creating object and let the subclasses decide which class to instantiate.
The factory method in the interface lets a class defers the instantiation to one or more concrete subclasses.
*/
public class B_FactoryDP {
    public static void main(String[] args) {
        Notification notification = NotificationFactory.getNotification("Push");

        notification.notifyUser();

    }
}

class NotificationFactory {

    static public Notification getNotification(String type){
        switch (type){
            case "Push":
                return new PushNotification();
            case "Email":
                return new EmailNotification();
            case "SMS":
                return new SMSNotification();
            default:
                throw new IllegalArgumentException("Notification channel not found");
        }

    }
}

class PushNotification implements Notification{

    @Override
    public void notifyUser() {
        System.out.println("PushNotification Sent");
    }
}

class EmailNotification implements Notification{

    @Override
    public void notifyUser() {
        System.out.println("EmailNotification Sent");
    }
}

class SMSNotification implements Notification{

    @Override
    public void notifyUser() {
        System.out.println("SMSNotification Sent");
    }
}
interface Notification {
    void notifyUser();
}