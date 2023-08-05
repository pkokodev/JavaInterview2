package multithreadings;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
-----------------The need for Callable-------------------

There are two ways of creating threads – one by extending the Thread class and other by creating a thread with a Runnable.
However, one feature lacking in  Runnable is that we cannot make a thread return result when it terminates, i.e. when run() completes.
For supporting this feature, the Callable interface is present in Java.

When the call() method terminates, the returned object must be stored that is known to the main thread.
It is important because the main thread must know the result generated in the call() method. To accomplish the same, a Future object is used.
A Future object holds the result obtained from the different thread, which is sent from the call() method.

Just like Callable, Future is also an interface. Therefore, to use it, its implementation is a must.
However, we do not have to take the pain to implement the Future interface.
The Java library already has the class called, FutureTask that implements the Runnable as well as the Future interfaces.

 Note that a thread can’t be created with a Callable, it can only be created with a Runnable. i.e there is no constructor of Thread
 which takes Callable as arguments.

To create the thread, a Runnable is required. To obtain the result, a Future is required.

The Java library has the concrete type FutureTask, which implements Runnable and Future, combining both functionality conveniently.
A FutureTask can be created by providing its constructor with a Callable. Then the FutureTask object is provided to the constructor
of Thread to create the Thread object. Thus, indirectly, the thread is created with a Callable.

For further emphasis, note that there is no way to create the thread directly with a Callable.



*/
public class A_Callable {
    public static void main(String[] args) {

        threadWithCallable();
        multipleTasks();


    }

    private static void multipleTasks() {
        Callable<String> callable = () -> "I am from thread " + Thread.currentThread().getName();

        FutureTask<String>[] futureTasks = new FutureTask[10];
        for (int i = 0; i < 10; i++){
            futureTasks[i] = new FutureTask<>(callable);
            //Thread constructor takes only Runnable interface as argument & FutureTsk implements Runnable and Future
            //But Future interface does not implement anyone hence can not be in Thread constructor
            Thread thread = new Thread(futureTasks[i]);
            thread.start();
        }

        try {
            for (int i = 0; i < 10; i++){

                System.out.println(futureTasks[i].get());
            /* Output:-
I am from thread Thread-0
I am from thread Thread-1
I am from thread Thread-2
I am from thread Thread-3
I am from thread Thread-4
I am from thread Thread-5
I am from thread Thread-6
I am from thread Thread-7
I am from thread Thread-8
I am from thread Thread-9
            */

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static void threadWithCallable() {
        Callable<String> callable = () -> "I am from thread " + Thread.currentThread().getName();

        //Directly can not create tread from callable hence need to create FutureTask object to use this callable
        //FutureTask with String as result
        FutureTask<String> futureTask = new FutureTask<>(callable);

        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            System.out.println(futureTask.get());// I am from thread Thread-0
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
