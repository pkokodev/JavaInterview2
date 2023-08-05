package multithreadings;
/*
* When more than one thread try to access same resource without synchronization causes "race condition".
* So we can solve race condition by using either "synchronized block" or "synchronized method".
* When no two threads can access same resource at a time phenomenon is also called as "mutual exclusion".
  It means that only one thread or process can execute a block of code (critical section) at a time.
*Visibility: It means that changes made by one thread to shared data are visible to other threads.
volatile keyword is used for such static variables

* Java’s synchronized keyword guarantees both mutual exclusion and visibility. If we make the blocks of threads that modify the
value of the shared variable synchronized only one thread can enter the block and changes made by it will be reflected in the main memory.
All other threads trying to enter the block at the same time will be blocked and put to sleep.

In some cases, we may only desire visibility and not atomicity. The use of synchronized in such a situation is overkill and may cause
scalability problems. Here volatile comes to the rescue. Volatile variables have the visibility features of synchronized but not
the atomicity features. The values of the volatile variable will never be cached and all writes and reads will be done to and from the
main memory. However, the use of volatile is limited to a very restricted set of cases as most of the times atomicity is desired.
For example, a simple increment statement such as x = x + 1; or x++ seems to be a single operation but is really a compound read-modify-write
sequence of operations that must execute atomically.

Thread safe Collections -

ConcurrentHashMap
Thread safe without having to synchronize the whole map Very fast reads while write is done with a lock No locking at the object level Uses multitude of locks.

SynchronizedHashMap
Object level synchronization Both read and writes acquire a lock Locking the collection has a performance drawback May cause contention

Vector

HashTable

CopyOnWriteArrayList

CopyOnWriteArraySet

Stack

Rest all are not thread safe
final List<String> synList = Collections.synchronizedList(new ArrayList<>());
add remove get...all operations will be sync
but for iterating over the list need to use synchronized

As an example, Let's say two threads run addAllon your list, with 2 different lists (A=A1,A2,A3 and B=B1,B2,B3) as parameter.

As the method is synchronized, you can be sure those lists won't be merged randomly like A1,B1,A2,A3,B2,B3

You don't decide when a thread handover the process to the other thread. Each method call has to fully run and return before the
other one could run.
So you can either get A1,A2,A3,B1,B2,B3 or B1,B2,B3,A1,A2,A3 (As we don't know which thread call will run first).


*/
public class B_Concurrency {
    public static void main(String[] args) {
        BTicket bTicket = new BTicket();
        //Using method reference if only one statement
        Thread thread0 = new Thread(bTicket::bookTicket);

        Thread thread1 = new Thread(bTicket::bookTicket);

        Thread thread2 = new Thread(() -> {
            bTicket.bookTicket();
        });

        thread0.start();
        thread1.start();
        thread2.start();

        /*
        Here all the three threads are working on same bTicket object so only one thread is allowed to get the lock (Object level locking).
        If we pass three different objs to three different thread then all these threads can enter in critical section
        at the same time, in this case need to make block or method as static (Class level locking).
        */
    }
}
class BTicket{
int available = 1;
  void bookTicket(){
        System.out.println("New Request");
      synchronized (this){
          System.out.println("available: " + available + "  " + Thread.currentThread().getName());
          if (available > 0) {
              try {
                  Thread.sleep(1000);
              } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              }
              System.out.println("Started " + Thread.currentThread().getName());
              available--;
              System.out.println("Booked " + Thread.currentThread().getName());
          } else {
              System.out.println("No Seats available");
          }
      }
    }
}

/*
Order of execution of threads is not guaranteed in both the cases
--------Output:- Race Condition -----------
All threads sees 1 availble seats and endup booking seats by all threads

New Request
New Request
available: 1  Thread-2
available: 1  Thread-0
available: 1  Thread-1
Started Thread-0
Started Thread-2
Booked Thread-0
Booked Thread-2
Started Thread-1
Booked Thread-1

------------Output:- Method Synchronized----------
Here only one passenger is able to book the seat

New Request
available: 1  Thread-0
Started Thread-0
Booked Thread-0
New Request
available: 0  Thread-2
No Seats available
New Request
available: 0  Thread-1
No Seats available


------------Output:- Block Synchronized----------
Here only one passenger is able to book the seat

New Request
New Request
New Request
available: 1  Thread-0
Started Thread-0
Booked Thread-0
available: 0  Thread-1
No Seats available
available: 0  Thread-2
No Seats available

*/
