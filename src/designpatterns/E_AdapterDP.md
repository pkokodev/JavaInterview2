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
        UPIPayment webAppPGToMobileAppPGAdapter = new WebAppPGToMobileAppPGAdapter(cardPayment);

        mobileApp.doPayment(webAppPGToMobileAppPGAdapter);

    }
}



class WebApp{

    public void doPayment(CardPayment cardPayment) {
        cardPayment.doPayment();
    }
}

class MobileApp {

    public void doPayment(UPIPayment upiPayment) {
        upiPayment.doPayment();
    }
}

class WebAppPG implements CardPayment {

    @Override
    public void doPayment() {
        System.out.println("Payment Done");
    }
}

class MobileAppPG implements UPIPayment {

    @Override
    public void doPayment() {
        System.out.println("Payment Done");
    }
}


interface UPIPayment {
    void doPayment();
}

interface CardPayment {
    void doPayment();
}

// Now we want to accept UPI payment on webapp as well

class WebAppPGToMobileAppPGAdapter implements UPIPayment {

    CardPayment cardPayment;
    WebAppPGToMobileAppPGAdapter(CardPayment cardPayment){
        this.cardPayment = cardPayment;
    }
    @Override
    public void doPayment() {
        cardPayment.doPayment();
    }
}

