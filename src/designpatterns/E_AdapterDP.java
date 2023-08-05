package designpatterns;
/*
----------Adapter Pattern------------
ADP is structural design pattern allows objects with incompatible interfaces to collaborate with one another
In real world examples we mostly use this pattern to work with legacy code
*/
public class E_AdapterDP {
    public static void main(String[] args) {
        WebApp webApp = new WebApp();
        CardPayment cardPayment = new WebAppPG();
        webApp.doPayment(cardPayment);

        MobileApp mobileApp = new MobileApp();
        UPIPayment upiPayment = new MobileAppPG();
        mobileApp.doPayment(upiPayment);

        //Now we want to accept UPI payment on webapp as well
        MobileAppToWebAppPaymentAdapter mobileAppToWebAppPaymentAdapter = new MobileAppToWebAppPaymentAdapter(upiPayment);

        webApp.doPayment(mobileAppToWebAppPaymentAdapter);

        /*
        Output:-
Payment Method: Card
Payment Method: UPI
Payment Method: UPI
        */
    }
}


interface UPIPayment {
    void doPayment();
}


interface CardPayment {
    void doPayment();
}
class MobileApp {

    public void doPayment(UPIPayment upiPayment) {
        upiPayment.doPayment();
    }
}

class WebAppPG implements CardPayment {

    @Override
    public void doPayment() {
        System.out.println("Payment Method: Card");
    }
}

class WebApp{

    public void doPayment(CardPayment cardPayment) {
        cardPayment.doPayment();
    }
}


class MobileAppPG implements UPIPayment {

    @Override
    public void doPayment() {
        System.out.println("Payment Method: UPI");
    }
}


// Now we want to accept UPI payment on webapp as well

class MobileAppToWebAppPaymentAdapter implements CardPayment {

    UPIPayment upiPayment;
    MobileAppToWebAppPaymentAdapter(UPIPayment upiPayment){
        this.upiPayment = upiPayment;
    }
    @Override
    public void doPayment() {
        upiPayment.doPayment();
    }
}

