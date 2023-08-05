package multithreadings;

import java.util.concurrent.*;

public class AsyncTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(getData2());
    }

    public static String getData() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<String> future = executorService.submit(()->{
            return "Hello world";
        } );

        String d = future.get();
        return d;
    }
    public static String getData2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            return "Raj";
        });

        CompletableFuture<String> greetingFuture = completableFuture.thenApply(name -> {
            return "Hello " + name;
        });

        String data = greetingFuture.get();
        return data;
    }
}
