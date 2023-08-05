package multithreadings;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

public class E_ProdCon {
    public static void main(String[] args) {

        /*Data data = new Data(5);

        Thread thread0 = new Thread(() -> {
            try {
                data.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread1 = new Thread(() -> {
            try {
                data.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


        thread0.start();
        thread1.start();*/

        UsingBQ usingBQ = new UsingBQ(5);
        Thread thread0 = new Thread(() -> {
            try {
                usingBQ.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread1 = new Thread(() -> {
            try {
                usingBQ.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });


        thread0.start();
        thread1.start();


    }
}

class Data{

    private Queue<Integer> queue;
    private Integer size;

    private int n = 20;

    Data(Integer size){
        this.size = size;
        this.queue =  new LinkedList<>();
    }

    //Consumer
    void consume() throws InterruptedException {
        int c = 0;
        synchronized (this){
            while (n > 0) {
                c++;
                n--;
                if(queue.isEmpty()) wait();
                System.out.println(Thread.currentThread().getName() + " consumed " + queue.peek());
                queue.poll();
                Thread.sleep(1000);
                notifyAll();
            }
        }
        System.out.println("Loop iteration count " + c);
    }

    //Producer
    void produce() throws InterruptedException {
        int c = 0;
        synchronized (this){
            while (n > 0) {
                c++;
                n--;
                if(queue.size() == size) wait();
                queue.offer(n);
                System.out.println( Thread.currentThread().getName() + " produced " + n);
                Thread.sleep(1000);
                notifyAll();
            }
        }
        System.out.println("Loop iteration count " + c);
    }
}

/*

Output:-
Without wait() notify(): Even if no data thread0(Consumer) keep consuming the data or thread1(Producer) keep producing the data

Thread-0 consumed null
Thread-0 consumed null
Thread-0 consumed null
Thread-0 consumed null
Thread-0 consumed null
Thread-0 consumed null
Thread-0 consumed null
Thread-0 consumed null
Thread-0 consumed null
Thread-0 consumed null
Thread-0 consumed null
Thread-0 consumed null
Thread-0 consumed null
Thread-0 consumed null
Thread-0 consumed null
Thread-0 consumed null
Thread-0 consumed null
Thread-0 consumed null
Thread-0 consumed null
Thread-0 consumed null
Loop iteration count 20
Loop iteration count 0

OR

Thread-1 produced 19
Thread-1 produced 18
Thread-1 produced 17
Thread-1 produced 16
Thread-1 produced 15
Thread-1 produced 14
Thread-1 produced 13
Thread-1 produced 12
Thread-1 produced 11
Thread-1 produced 10
Thread-1 produced 9
Thread-1 produced 8
Thread-1 produced 7
Thread-1 produced 6
Thread-1 produced 5
Thread-1 produced 4
Thread-1 produced 3
Thread-1 produced 2
Thread-1 produced 1
Thread-1 produced 0
Loop iteration count 20
Loop iteration count 0

Output:-
With wait() notify()

Thread-1 produced 18
Thread-1 produced 17
Thread-1 produced 16
Thread-1 produced 15
Thread-1 produced 14
Thread-0 consumed 18
Thread-0 consumed 17
Thread-0 consumed 16
Thread-0 consumed 15
Thread-0 consumed 14
Thread-1 produced 8
Thread-1 produced 7
Thread-1 produced 6
Thread-1 produced 5
Thread-1 produced 4
Thread-0 consumed 8
Thread-0 consumed 7
Thread-0 consumed 6
Thread-0 consumed 5
Thread-1 produced 0
Loop iteration count 9
Loop iteration count 11


*/


class UsingBQ {

    private BlockingQueue<Integer> blockingDeque;
    int n = 20;

    UsingBQ(Integer size){
        this.blockingDeque =  new LinkedBlockingQueue<>(size);
    }

    //Consumer
    void consume() throws InterruptedException {
        while (n > 0){
            System.out.println("consumed " + blockingDeque.take());
            Thread.sleep(1000);
        }
    }

    //Producer
    void produce() throws InterruptedException {
        while (n > 0){
            blockingDeque.put(n);
            System.out.println("produced " + n);
            Thread.sleep(1000);
            n--;
        }
    }
}


/*

consumed 20
produced 20
produced 19
consumed 19
produced 18
consumed 18
produced 17
consumed 17
produced 16
consumed 16
produced 15
consumed 15
produced 14
consumed 14
produced 13
consumed 13
produced 12
consumed 12
produced 11
consumed 11
produced 10
consumed 10
produced 9
consumed 9
produced 8
consumed 8
produced 7
consumed 7
produced 6
consumed 6
produced 5
consumed 5
produced 4
consumed 4
produced 3
consumed 3
produced 2
consumed 2
produced 1
consumed 1
*/