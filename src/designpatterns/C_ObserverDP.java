package designpatterns;

import java.util.ArrayList;

/*
------------Observer Pattern-------------

Definition:

The Observer Pattern defines a one to many dependency between objects so that one object changes state, all of its dependents are to be
notified and updated automatically.

Explanation:

One to many dependency is between Subject(One) and Observer(Many).
There is dependency as Observers themselves don’t have access to data. They are dependent on Subject to provide them data.

Real Life Uses:
Notification Delivery
It is heavily used in GUI toolkits and event listener. In java the button(subject) and onClickListener(observer) are modelled with observer pattern.
YouTube Subscribe, Instagram follow

we can iterate through list and send notifications but suppose we want add new or remove existing notification type like add whatspp
in this case below code will work seamlessly
just creaate new WhatsppNotification{} and subscribe
*/
public class C_ObserverDP {
    public static void main(String[] args) {
        Store store = new Store();
        PushNotification pushNotification = new PushNotification();

        store.getNotificationService().subscribe(new EmailNotification());
        store.getNotificationService().subscribe(pushNotification);
        store.getNotificationService().subscribe(new SMSNotification());

        store.addNewProduct();
        store.getNotificationService().unSubscribe(pushNotification);
        store.addNewProduct();
    }
}

class Store {
    private NotificationService notificationService;
    Store(){
        notificationService = new NotificationService();
    }

    void addNewProduct(){
        notificationService.notifys();
    }

    NotificationService getNotificationService() {
        return notificationService;
    }
}

//Subject
class NotificationService {
    ArrayList<Notification> notificationListeners;
    NotificationService(){
        notificationListeners = new ArrayList<>();
    }

    //her Listeners can be push email or sms
    void subscribe(Notification listener){
        notificationListeners.add(listener);
    }

    void unSubscribe(Notification listener){
        notificationListeners.remove(listener);
    }

    void notifys(){
        for (Notification listener:
             notificationListeners) {
            listener.notifyUser();
        }
    }
}

/*
Output:-

EmailNotification Sent
PushNotification Sent
SMSNotification Sent
EmailNotification Sent
SMSNotification Sent
*/