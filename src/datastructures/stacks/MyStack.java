package datastructures.stacks;


/* Java program to implement basic stack
All operations takes O(1)

Using Array

It is not dynamic.
It doesn’t grow and shrink depending on needs at runtime.
The total size of the stack must be defined before.

Using LL
The linked list implementation of a stack can grow and shrink according to the needs at runtime.
It is used in many virtual machines like JVM.
Stacks are more secure and reliable as they do not get corrupted easily.
Stack cleans up the objects automatically.
Random accessing is not possible in stack.
 */
public class MyStack {
    int capacity;
    int top;
    int[] a;

    boolean isEmpty()
    {
        return (top < 0);
    }
    MyStack(int capacity)
    {
        this.capacity = capacity;
        a = new int[capacity];
        top = -1;
    }

    boolean push(int x)
    {
        if (top >= (capacity - 1)) {
            System.out.println("Stack Overflow");
            return false;
        }
        else {
            a[++top] = x;
            System.out.println(x + " pushed into stack");
            return true;
        }
    }

    int pop()
    {
        if (top < 0) {
            System.out.println("Stack Underflow");
            return 0;
        }
        else {
            int x = a[top--];
            return x;
        }
    }

    int peek()
    {
        if (top < 0) {
            System.out.println("Stack Underflow");
            return 0;
        }
        else {
            int x = a[top];
            return x;
        }
    }

    void print(){
        for(int i = top;i>-1;i--){
            System.out.print(" "+ a[i]);
        }
    }
}

// Driver code
class Main {
    public static void main(String args[])
    {
        MyStack s = new MyStack(3);
        s.push(10);
        s.push(20);
        s.push(30);
        System.out.println(s.pop() + " Popped from stack");
        System.out.println("Top element is :" + s.peek());
        System.out.print("Elements present in stack :");
        s.print();
    }
}