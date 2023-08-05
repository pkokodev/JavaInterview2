package multithreadings;
/*
If two or more threads are waiting for each other to finish, then the condition is known as Deadlock

To resolve this deadlock remove the cyclic dependency by changing the order of the locks prevent the program

*/
public class C_Deadlock {
    public static void main(String[] args) {
        TicketCounter ticketCounter = new TicketCounter();
        Thread thread0 = new Thread(() -> ticketCounter.counter0());

        Thread thread1 = new Thread(() -> ticketCounter.counter1());

        thread0.start();
        thread1.start();
    }
}

class TicketCounter {
    Object lock0 = new Object();
    Object lock1 = new Object();
    void counter0(){
        synchronized (lock0){
            try {
                System.out.println("counter0 started: " + Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lock1){
                System.out.println("counter0 completed: " + Thread.currentThread().getName());
            }
        }

    }

    void counter1(){
        synchronized (lock1){
            {
                System.out.println("counter1 started: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock0){
                    System.out.println("counter1 completed: " + Thread.currentThread().getName());
                }            }
        }
    }
    // To resolve this deadlock remove the cyclic dependency by changing the order of the locks prevent the program
    void counter2(){
        synchronized (lock0){
            try {
                System.out.println("counter0 started: " + Thread.currentThread().getName());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lock1){
                System.out.println("counter0 completed: " + Thread.currentThread().getName());
            }
        }

    }

    void counter3(){
        synchronized (lock0){
            {
                System.out.println("counter1 started: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock1){
                    System.out.println("counter1 completed: " + Thread.currentThread().getName());
                }            }
        }
    }
}

/*

counter0 started: Thread-0
counter1 started: Thread-1

output: After

counter0 started: Thread-0
counter0 completed: Thread-0
counter1 started: Thread-1
counter1 completed: Thread-1

*/