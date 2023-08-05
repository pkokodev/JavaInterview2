package multithreadings;
/*
Thrads are light weight processes within the process, and memory allocated to that process will be shared among all threads
Hence it is important to manage the shared resources while we are working in multithreaded environment. But Per Thread/ Per Stack memory

----------Two ways two create threads in JAVA-----------

1) extends Thread & @Override run()
2) implements Runnable & @Override run() (Best way)

* When you extend Thread class, you can’t extend any other class which you require.
(As you know, Java does not allow inheriting more than one class).
When you implement Runnable, you can save a space for your class to extend any other class in future or now.

However, the significant difference is.

* When you extends Thread class, each of your thread creates unique object and associate with it.
When you implements Runnable, it shares the same object to multiple threads.

* Order of execution is not predictable i.e may give different result at each program run
        
        <public class A_Basic {
            public static void main(String[] args) {
                Ticket1 ticket1 = new Ticket1();
                ticket1.start();
                //Calling again start() not possible throws IllegalStateException, need to create another object but using implements multiple threads can work on same object
                //ticket1.start();
        
                //Calling run() for both the types, no multithreading occurred, all code will be executed by main thread
                //ticket1.run();
        
                //------Using Implements--------
        
                Ticket2 ticket2 = new Ticket2();
                Thread thread1 = new Thread(ticket2);
                thread1.start();
        
                Thread thread2 = new Thread(ticket2);
                thread2.start();
        
                //Using lambda expression Anonymous class implementation of Runnable
                String str = new String("MyString");
                Thread thread3 = new Thread(() ->{
                    System.out.println("Anonymous class: " + Thread.currentThread().getName() + "  " + str.substring(1));
        
                });
                thread3.start();
        
                //thread1 and thread2 are working on ticket objects while thread3 is working on str object
            }
        }
        >
        class Ticket1 extends Thread {
        
            @Override
            public void run() {
                bookTicket();
            }
        
            void bookTicket(){
                System.out.println("Started");
                System.out.println(Thread.currentThread().getName());
                System.out.println("Completed");
            }
        }
        class Ticket2 implements Runnable {
        
            @Override
            public void run() {
                bookTicket();
            }
        
            //Critical Section, needs to be synchronized
            void bookTicket(){
                System.out.println("Started");
                System.out.println(Thread.currentThread().getName());
                System.out.println("Completed");
            }
        }
