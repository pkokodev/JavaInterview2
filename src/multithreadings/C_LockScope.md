package multithreadings;

public class C_LockScope {

    /*
    Object lock vs Class clock
    Without static two threads can change state of two different objects
    But with static or (synchronized(Ticket.class) only one thread allowed to enter in critical section
    Variable level lock can be done using AtomicWrapperClasses
    */
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        Ticket Ticket = new Ticket();

        Thread t0 = new Thread(()->{
            ticket.book1(1);
        });
        Thread t1 = new Thread(()->{
            ticket.book2(1);
        });
        Thread t2 = new Thread(()->{
            Ticket.book1(1);
        });
        Thread t3 = new Thread(()->{
            Ticket.book2(1);

        });

        t0.start();
        t1.start();
        t2.start();
        t3.start();
        //Variable level lock
        X x = new X();
        Thread t4 = new Thread(()->{
            x.addA();

        });
        Thread t5 = new Thread(()->{
            x.addB();

        });

        t4.start();
        t5.start();
    }
}



class Ticket {
     private static int available = 2;

         static synchronized void book1(int quantity) {
             try {
                 System.out.println( " Book1 : "+ available + " available " +  Thread.currentThread().getName() + " started booking");
                 Thread.sleep(2000);
                 if(available > 0){
                     available -= quantity;
                     System.out.println( " Book1 : "+ available + " available " +  Thread.currentThread().getName() + " completed booking");

                 }else {
                     System.out.println( " Book1 : "+ available + " available " +  Thread.currentThread().getName() + " no seats");
                 }
                 Thread.sleep(2000);
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }

    }
    static synchronized void book2(int quantity) {
             try {
                 System.out.println( " Book2 : "+ available + " available " +  Thread.currentThread().getName() + " started booking");
                 Thread.sleep(2000);
                 if(available > 0){
                     available -= quantity;
                     System.out.println( " Book2 : "+ available + " available " +  Thread.currentThread().getName() + " completed booking");

                 }else {
                     System.out.println( " Book2 : "+ available + " available " +  Thread.currentThread().getName() + " no seats");
                 }
                 Thread.sleep(2000);
             } catch (InterruptedException e) {
                 throw new RuntimeException(e);
             }

    }

}

class X {

    private static volatile Integer a = 0;
    private Integer b = 0;

    private final Object lock1 = new Ticket();
    private final Object lock2 = new Ticket();

    //Here lock on two different objects hence can enter two threads simultaneously
    public void addA(){
        synchronized (lock1){
            try {
                System.out.println(" addA " + Thread.currentThread().getName() + " entered: " + a + " a ");
                Thread.sleep(2000);
                a++;
                System.out.println(" addA " + Thread.currentThread().getName() + " leaving: " + a + " a ");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public void addB(){
        synchronized (lock2){
            try {
                System.out.println(" addB " + Thread.currentThread().getName() + " entered: " + b + " b ");
                Thread.sleep(2000);
                b++;
                System.out.println(" addB " + Thread.currentThread().getName() + " leaving: " + b + " b ");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }


}

/*
Book1 & Book2 both are static so only one will be executed at a time i.e. we cant Book2 see before "Book1 completed"
addA & addB are object level locks so can enter two threads simultaneously.

Ouptput:-

 Book1 : 2 available Thread-0 started booking
 addB Thread-5 entered: 0 b
 addA Thread-4 entered: 0 a
 Book1 : 1 available Thread-0 completed booking
 addA Thread-4 leaving: 1 a
 addB Thread-5 leaving: 1 b
 Book2 : 1 available Thread-3 started booking
 Book2 : 0 available Thread-3 completed booking
 Book1 : 0 available Thread-2 started booking
 Book1 : 0 available Thread-2 no seats
 Book2 : 0 available Thread-1 started booking
 Book2 : 0 available Thread-1 no seats

*/