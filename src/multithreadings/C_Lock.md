package multithreadings;

public class C_Lock
{
    public static void main(String[] args) {

        Account account = new Account(0);

        Thread thread0 = new Thread(() -> {
            account.add(500);
        });

        Thread thread1 = new Thread(() -> {
            account.withdraw(500);
        });

        thread0.start();
        thread1.start();

    }
}

class Account {
    int balance = 0;

    public Account(int balance) {
        this.balance = balance;
    }

     synchronized void add(int amount){
        System.out.println("Add Request " + Thread.currentThread().getName());
        System.out.println("Balance " + balance + " Add Started " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        balance += amount;
        System.out.println("Balance " + balance + " Add Completed " + Thread.currentThread().getName());
    }

     synchronized void withdraw(int amount){
        System.out.println("Withdraw Request " + Thread.currentThread().getName());
        System.out.println("Balance " + balance + " Withdraw Started " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        balance -= amount;
        System.out.println("Balance " + balance + " Withdraw Completed " + Thread.currentThread().getName());
    }
}

/*
Output:-
Without synchronized

Withdraw Request Thread-1
Add Request Thread-0
Balance 0 Withdraw Started Thread-1
Balance 0 Add Started Thread-0
Balance -500 Withdraw Completed Thread-1
Balance 0 Add Completed Thread-0

Output:-
One method synchronized. Still the inconsistency result

Withdraw Request Thread-1
Add Request Thread-0
Balance 0 Withdraw Started Thread-1
Balance 0 Add Started Thread-0
Balance 0 Add Completed Thread-0
Balance -500 Withdraw Completed Thread-1

Output:-
Both the methods synchronized
Only one thread can have lock even if calling different methods. After completing the add method withdraw method is entering.

Add Request Thread-0
Balance 0 Add Started Thread-0
Balance 500 Add Completed Thread-0
Withdraw Request Thread-1
Balance 500 Withdraw Started Thread-1
Balance 0 Withdraw Completed Thread-1

*/