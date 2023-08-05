package javacore.inheritance;

import java.io.IOException;
import java.rmi.RemoteException;
import java.sql.SQLException;


/*
If SuperClass does not declare an exception, then the SubClass can only declare unchecked exceptions, but not the checked exceptions.
If SuperClass declares an exception, then the SubClass can only declare the same or child exceptions of the exception declared by the SuperClass and any new Runtime Exceptions, just not any new checked exceptions at the same level or higher.
If SuperClass declares an exception, then the SubClass can declare without exception.
*/

/*
If exception(checked/unchecked) is thrown in the parent class’s method then child class’s overridden method
is not forced to through an exception. But if we call using super then should be throw the same exception

However it can through the exception if it wants(rules will apply).

 If the child is throwing any unchecked exception then parent need not to throw exception.

If the child class is throwing any checked exception then parent must also through same exception
(OR) any of its parent exception otherwise compilation fails.
http://javainsimpleway.com/exception-in-inheritance/
*/
public class ExceptionTest {
    public static void main(String[] args) {
        ChildB c = new ChildB();
        c.print();

    }

}

class ParentA{
     void print() throws IOException {
        System.out.println("From parent");
    }
}

class ChildB extends ParentA{

    @Override
     void print() throws NullPointerException {
        System.out.println("From Child");
    }
}