package multithreadings;
/*
Given an integer N, the task is to write Java Program to print the first N natural numbers in increasing order using two threads.
Print even and odd numbers in increasing order using two threads in Java.
It is type of producer consumer example.

*/
public class D_ThreadComm {
    public static void main(String[] args) {
        Printer printer = new Printer(20);
        Thread thread0 = new Thread(() -> {
            try {
                printer.printEven();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread1 = new Thread(() -> {
            try {
                printer.printOdd();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread0.start();
        thread1.start();
    }
}

class Printer{
    int counter = 1;
    static int n = 0;

    public Printer(int i) {
        n = i;
    }

    void printOdd() throws InterruptedException {
        int c = 0;
        synchronized (this){
            while (counter < n) {
                c++;
                //"while" is added instead of "if" for spurious wake up" of threads
                while (counter % 2 == 0) {
                    wait(); //Can only be called in synchronized block else throws exception
                }
                System.out.println(Thread.currentThread().getName() + "  " + counter++);
                notifyAll(); //Can only be called in synchronized block else throws exception. If more than one thread waiting then notifyAll();
            }
        }
        System.out.println("Loop iterations " + c);
    }

    void printEven() throws InterruptedException {
        int c = 0;
        synchronized (this){
            while (counter < n) {
                c++;
                //"while" is added instead of "if" for spurious wake up" of threads
                while (counter % 2 != 0) {
                    wait();
                }
                System.out.println(Thread.currentThread().getName() + "  " + counter++);
                notifyAll();
            }
        }
        System.out.println("Loop iterations " + c);
    }
}


/*
void printOdd(){
        int c = 0;
        while (counter <= n){
            c++;
            if (counter % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + "  " +counter++);
            }
        }
        System.out.println("Loop iterations " + c);
    }

    void printEven(){
        int c = 0;
        while (counter <= n){
            c++;
            if (counter % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + "  " +counter++);
            }
        }
        System.out.println("Loop iterations " + c);
    }

Output:-
Multiple issues in this case
1) Sequence is not maintained so we need to use synchronized block or method
2) Loop is executed so many times just to print 20 values. Hence, complexity is so big
   So need to use thread communication so that threads can wait and wakeup to do the task

Thread-1  1
Thread-1  3
Thread-0  2
Thread-1  5
Thread-0  4
Thread-1  7
Thread-0  6
Thread-1  9
Thread-0  8
Thread-1  11
Thread-0  10
Thread-1  13
Thread-0  12
Thread-1  15
Thread-0  14
Thread-1  17
Thread-0  16
Thread-1  19
Thread-0  18
Thread-0  20
Loop iterations 16384
Loop iterations 1207

Output:-
Thread-1  1
Thread-0  2
Thread-1  3
Thread-0  4
Thread-1  5
Thread-0  6
Thread-1  7
Thread-0  8
Thread-1  9
Thread-0  10
Thread-1  11
Thread-0  12
Thread-1  13
Thread-0  14
Thread-1  15
Thread-0  16
Thread-1  17
Thread-0  18
Thread-1  19
Thread-0  20
Loop iterations 10
Loop iterations 10
*/