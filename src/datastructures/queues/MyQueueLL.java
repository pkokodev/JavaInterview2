package datastructures.queues;

// Java program for linked-list implementation of queue

// A linked list (LL) node to store a queue entry
class QNode {
    int data;
    QNode next;

    // constructor to create a new linked list node
    public QNode(int data)
    {
        this.data = data;
        this.next = null;
    }
}

// A class to represent a queue
// The queue, front stores the front node of LL and rear stores the
// last node of LL
class MyQueueLL {
    QNode front, rear;

    public MyQueueLL()
    {
        this.front = this.rear = null;
    }

    // Method to add an data to the queue.
    void enqueue(int data)
    {

        // Create a new LL node
        QNode temp = new QNode(data);

        // If queue is empty, then new node is front and rear both
        if (this.rear == null) {
            this.front = this.rear = temp;
            return;
        }

        // Add the new node at the end of queue and change rear
        this.rear.next = temp;
        this.rear = temp;
    }

    // Method to remove an data from queue.
    void dequeue()
    {
        // If queue is empty, return NULL.
        if (this.front == null)
            return;

        // Store previous front and move front one node ahead
        QNode temp = this.front;
        this.front = this.front.next;

        // If front becomes NULL, then change rear also as NULL
        if (this.front == null)
            this.rear = null;
    }
}

// Driver class
 class Test {
    public static void main(String[] args)
    {
        MyQueueLL q = new MyQueueLL();
        q.enqueue(10);
        q.enqueue(20);
        q.dequeue();
        q.dequeue();
        q.enqueue(30);
        q.enqueue(40);
        q.enqueue(50);
        q.dequeue();
        System.out.println("Queue Front : " + q.front.data);
        System.out.println("Queue Rear : " + q.rear.data);
    }
}
// This code is contributed by Gaurav Miglani
