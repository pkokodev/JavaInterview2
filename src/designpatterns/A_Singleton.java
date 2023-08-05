package designpatterns;
/*

SingletonDesign Pattern:-

Singleton pattern is generally useful when the object that is created once and shared across threads/Applications.
Also reflect the changes and location will be global and unique through out the application

* Some real life use cases:
Third API configuration
Database connections
Config files

Eg. Custom logger with to save in the file and file size limit is 1mb. in that case if the size limit exceeded then we want to
change filename, and it will be reflected across the application to use new file

Singleton class can be instantiated by two methods:

* Early initialization : In this method, class is initialized whether it is to be used or not. The main advantage of this method is its simplicity.
You initiate the class at the time of class loading. Its drawback is that class is always initialized whether it is being used or not.

* Lazy initialization : In this method, class in initialized only when it is required. It can save you from instantiating
the class when you don’t need it. Generally, lazy initialization is used when we create a singleton class.
*/
public class A_Singleton {
    public static void main(String[] args) {
        Database database1 = Database.getInstance();
        Database database2 = Database.getInstance();
        System.out.println(database1.hashCode());
        System.out.println(database2.hashCode());
    }
}

class Database {

    //Early:  //Created at time of class loading first time but not lazy
    //private static Database instance = new Database();
    private static Database instance;

    //Make Constructor private so that object can not be created
    private Database(){

    }
    //Lazy
    // getInstance() called many times hence, and it is expensive bcz of synchronized  so it causes slow performance a
    // s multiple threads can’t access it simultaneously.
    /*public synchronized static Database getInstance(){
        if(instance == null){
            instance = new Database();
            System.out.println("New Instance Created");
        }

        return instance;
    }*/
    public static Database getInstance(){
        if(instance == null){
            //Double check locking
            synchronized (Database.class){
                if(instance == null){
                    instance = new Database();
                    System.out.println("New Instance Created");
                }
            }
        }

        return instance;
    }

    public void connect(){
        System.out.println("Database Connection Succeeded");
    }
}

/* Best Way

* Bill Pugh Singleton Implementation: Prior to Java5, memory model had a lot of issues and above methods caused failure in certain scenarios
in multithreaded environment. So, Bill Pugh suggested a concept of inner static classes to use for singleton.

* When the singleton class is loaded, inner class is not loaded and hence doesn’t create object when loading the class.
Inner class is created only when getInstance() method is called. So it may seem like eager initialization but it is lazy initialization.
This is the most widely used approach as it doesn’t use synchronization.
*/


// Java code for Bill Pugh Singleton Implementation

 class GFG
{

    private GFG()
    {
        // private constructor
    }

    // Inner class to provide instance of class
    private static class BillPughSingleton
    {
        //Created at time of class loading first time
        private static final GFG INSTANCE = new GFG();
    }

    //Lazy Also
    public static GFG getInstance()
    {
        return BillPughSingleton.INSTANCE;
    }
}