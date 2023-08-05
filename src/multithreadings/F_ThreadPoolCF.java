package multithreadings;

import java.util.concurrent.*;

/*
The Java ExecutorService is the interface which allows us to execute tasks on threads asynchronously.
The Java ExecutorService interface is present in the java.util.concurrent package.
The ExecutorService helps in maintaining a pool of threads and assigns them tasks.
It also provides the facility to queue up tasks until there is a free thread available if the number of tasks
is more than the threads available.

Executor only has executed(Runnable)
interface ExecutorService extends Executor
<T> Future<T> submit(Callable<T> task); Need to pass FutureTask as task here
   <T> Future<T> submit(Runnable task, T result);

runAsync(Runnable)
supplyAsync(Supplier)
thenApply(Function) like map 
thenAccept(Consumer)
thenRun(Runnable)
thenCompose(Function) like flatMap used to chain CompletableFuture
handle(BiFunction) (result, error)
The code in above methods will start executing immediately after declaring.

All return CompletableFuture & these FI will not be executed untill we will not call get()
Also above methods have Async postfix versions used to execute the task again on different thread rather than the calling thread for more parallelism.
These methods also takes one more argument as Executor to execute tasks on Executor thread pool
else it will execute on ForkJoinPool.commonPool()
*/
public class F_ThreadPoolCF {
    public static void main(String[] args) {
        executorTest();


        try {
            System.out.println(calculateAsync().get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        /*
        The code above allows us to pick any mechanism of concurrent execution,
        but what if we want to skip this boilerplate and simply execute some code asynchronously?
        Static methods runAsync and supplyAsync allow us to create a CompletableFuture instance out of Runnable
        and Supplier functional types correspondingly.
        */


        //CompletableFuture with Encapsulated Computation Logic
        usingCompletableFutureDirectly();

        //Processing Results of Asynchronous Computations
        //processAsyncResult();

        //Combining Futures thenCompose(Function)
        //combineFutures();

        //8. Running Multiple Futures in Parallel
        //runParallel();

        //Error Handling
        //errorHandling(null);
    }

    private static void errorHandling(String data) {
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
            if (data == null) throw new RuntimeException("Data is null");
            return "Hello" + data;
        }).handle((result, error) -> {
            if (result != null) return result;
            return "Error occurred" + error.getMessage();
        });

        try {
            System.out.println(completableFuture.get());// Hellodata And if data null --> Error occurredjava.lang.RuntimeException: Data is null
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        //Or we can handle while get()

       /* completableFuture.completeExceptionally(
                new RuntimeException("Calculation failed!"));

                completableFuture.get(); // ExecutionException

*/
    }

    private static void runParallel() {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture future4 = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync Executed: " + Thread.currentThread().getName());
            return "supplyAsync result from: " + Thread.currentThread().getName();
        });

        //The CompletableFuture.allOf static method allows to wait for completion of all of the Futures provided as a var-arg:
        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(future1, future2, future3, future4);
        try {
            System.out.println(combinedFuture.get());//null
            /*
            Notice that the return type of the CompletableFuture.allOf() is a CompletableFuture<Void>.
            The limitation of this method is that it does not return the combined results of all Futures.
            Instead, we have to manually get results from Futures.
            Fortunately, CompletableFuture.join() method and Java 8 Streams API makes it simple:
            */

            //The CompletableFuture.join() method is similar to the get method, but it throws an unchecked exception
            // in case the Future does not complete normally. This makes it possible to use it as a method reference in the Stream.map() method.
            System.out.println(future1.join() + future2.join() + future3.join());//HelloBeautifulWorld
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static void combineFutures() {
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));

        try {
            System.out.println(completableFuture.get());//Hello world
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static void processAsyncResult() {
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync Executed: " + Thread.currentThread().getName());
            return "supplyAsync result from: " + Thread.currentThread().getName();
        });

        CompletableFuture completableFutureFinal = completableFuture.thenApply((result) -> {
            System.out.println("thenApply Executed: " + Thread.currentThread().getName());
            return "thenApply " + result;
        });
        try {
            //Supplier will be called only once for the first get() even though we are calling twice
            System.out.println(completableFuture.get());
            System.out.println(completableFutureFinal.get());
/*
output:-
supplyAsync Executed: ForkJoinPool.commonPool-worker-1
supplyAsync result from: ForkJoinPool.commonPool-worker-1
thenApply Executed: ForkJoinPool.commonPool-worker-1
thenApply supplyAsync result from: ForkJoinPool.commonPool-worker-1

Output:- if sout(completableFuture.get()) commented
supplyAsync Executed: ForkJoinPool.commonPool-worker-1
thenApply Executed: ForkJoinPool.commonPool-worker-1
thenApply supplyAsync result from: ForkJoinPool.commonPool-worker-1

*/
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }

    //Static methods runAsync and supplyAsync allow us to create a CompletableFuture instance out of Runnable and Supplier
    private static void usingCompletableFutureDirectly() {
        //Using Runnable i.e. runAsync(Runnable)
        try {
            //Here code will not run until we call get method on it
            CompletableFuture.runAsync(() -> System.out.println("runAsync From: " + Thread.currentThread().getName())).get();//From: ForkJoinPool.commonPool-worker-1
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        //Using Supplier i.e. supplyAsync(Supplier)
        try {
            //Here code will not run until we call get method on it
            String result = CompletableFuture.supplyAsync(() -> "supplyAsync From: " + Thread.currentThread().getName()).get();//From: ForkJoinPool.commonPool-worker-1
            System.out.println(result);//supplyAsync From: ForkJoinPool.commonPool-worker-1
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }


    //Asynchronous method:- Method which return CompletableFuture
    private static CompletableFuture<String> calculateAsync() {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.submit(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("From: " + Thread.currentThread().getName());
            completableFuture.complete("Task done and returned the result");
        });

        return completableFuture;

    }

    private static void executorTest() {
        //Executor interface having only single method void execute(Runnable command) also don't have shutdown method;
        Executor executor = Executors.newSingleThreadExecutor();
        //executor.execute(() -> System.out.println("From: " + Thread.currentThread().getName()));//From: pool-1-thread-1


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> System.out.println("From: " + Thread.currentThread().getName()));//From: pool-1-thread-1
        //Need to shut down the executorService explicitly otherwise program will not stop
        executorService.shutdown();

        //Passed Callable task and get the Future
        Future future = executorService.submit(() -> "Returned result from: " + Thread.currentThread().getName());
        try {
            System.out.println(future.get());//Returned result from: pool-1-thread-1
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
