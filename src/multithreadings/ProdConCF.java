package multithreadings;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

//Producer consumer using CompletableFuture
public class ProdConCF {
    static  int i = 0;
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        Object lock = new Object();
        CompletableFuture producer = CompletableFuture.runAsync(() ->{

            int count = 0;
                while (i < 10) {
                    count++;
                    synchronized (lock){
                    if (queue.size() == 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                        queue.add(i);
                /*try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }*/
                        System.out.println("produced " + i++ + " by " + Thread.currentThread().getName());
                    lock.notifyAll();
                }
            }
            System.out.println(count);
        });

        CompletableFuture consumer = CompletableFuture.runAsync(() ->{

            int count = 0;
                while (i < 10) {
                    count++;
                    synchronized (lock){
                    if (queue.isEmpty()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                /*try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }*/
                        System.out.println("consumed " + queue.peek() + " by " + Thread.currentThread().getName());
                        queue.poll();
                        lock.notifyAll();

                }
            }
            System.out.println(count);

        });

        CompletableFuture completableFuture = CompletableFuture.allOf(producer, consumer);
        try {
            completableFuture.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

/*
OutPut:- Using synchronization wait & notify

produced 0 by ForkJoinPool.commonPool-worker-1
consumed 0 by ForkJoinPool.commonPool-worker-2
produced 1 by ForkJoinPool.commonPool-worker-1
consumed 1 by ForkJoinPool.commonPool-worker-2
produced 2 by ForkJoinPool.commonPool-worker-1
consumed 2 by ForkJoinPool.commonPool-worker-2
produced 3 by ForkJoinPool.commonPool-worker-1
consumed 3 by ForkJoinPool.commonPool-worker-2
produced 4 by ForkJoinPool.commonPool-worker-1
consumed 4 by ForkJoinPool.commonPool-worker-2
produced 5 by ForkJoinPool.commonPool-worker-1
consumed 5 by ForkJoinPool.commonPool-worker-2
produced 6 by ForkJoinPool.commonPool-worker-1
consumed 6 by ForkJoinPool.commonPool-worker-2
produced 7 by ForkJoinPool.commonPool-worker-1
consumed 7 by ForkJoinPool.commonPool-worker-2
produced 8 by ForkJoinPool.commonPool-worker-1
consumed 8 by ForkJoinPool.commonPool-worker-2
produced 9 by ForkJoinPool.commonPool-worker-1
10
consumed 9 by ForkJoinPool.commonPool-worker-2
10
*/