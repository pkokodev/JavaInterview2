Contents (Level - Basic)
 TOP 20 interview questions
BASIC CONCEPT OF JAVA
TARGETED COMPANIES
PART - 1

1. **What is Java ?**


Java is a high-level programming language and is platform-independent.
Java is a general-purpose programming language that is class-based, object-oriented, and designed to have as few implementation dependencies as possible. 
There are a lot of applications, websites, and games that are developed using Java.


2. **What is JRE, and why is it required?**


JRE stands for "Java Runtime Environment" which you usually download as a Java software. 
The JRE comprises of the Java Virtual Machine, Java platform classes, and supporting libraries. **The JRE is the runtime component of Java software and is all you need to run any Java application.


3. **What is JDK, and why is it required?**


The JDK is a superset of the JRE and includes everything that the JRE contains. Additionally, it comes with the compilers and debuggers tools required for developing Java applications.


4. **What is JVM, and why is it required?**


JVM stands for The Java Virtual machine. It translates and executes the Java bytecode. It's the entity which transforms Java to become a "portable language" (i.e., write once, run anywhere). 
Though, each platform has its implementation of JVM like the Windows, Linux, macOS, etc. have a distinct version of JVM to run bytecode.


5. **What is JIT compiler?**


Just-In-Time(JIT) compiler: It is used to improve the performance. **JIT compiles parts of the bytecode that have similar functionality at the same time, and hence reduces the amount of time needed for compilation. 
Here the term “compiler” refers to a translator from the instruction set of a Java virtual machine (JVM) to the instruction set of a specific CPU.


6. **What do you mean by Java is platform independent?**


Platform independent means that we can write and compile the java code in one platform and can execute the class in any other supported platform.


7. **What gives Java its 'write once and run anywhere' nature?**


The bytecode. Java compiler converts the Java programs into the class file (Byte Code) which is the intermediate language between source code and machine code. 
This bytecode is not platform specific and can be executed on any computer.


8. **How your program would behave if you declare the main method as private ?**


It would get compiled correctly but will throw the error "Main method not public." at runtime.

Hence, it will provide runtime exception.


9. **What  if an application get multiple classes having main() methods?**


It's certainly possible to have multiple main methods in different classes. When you start the application, you've to provide the startup class name for execution.

 The JVM then looks up for the main method only in the class whose name you've supplied. 
Hence, you won't observe any conflict with the multiple classes having the <main()> definition.


10. **What is the difference between pass by reference and pass by value in Java?**


Pass by reference indicates, passing the address itself rather than passing the value. 

Pass by value means is giving a copy of the value.

Java deals with Pass by value, since Java doesn’t support pointers.


11. **What do you make of each keyword in public static void main(String args[])?**


Public- <main()> is the entry point method which the JVM calls when a program starts. So it is mandatory to be accessible from the Java environment. Hence, the access specifier has to be public.

Static- JVM must be capable of calling this method w/o creating an instance of the class. So the method has to be declared as static.						continue..


11. **What do you make of each keyword in public static void main(String args[])?**


Void- <main()> doesn't return anything, so its return type must be void.

The argument string represents the argument type passed from the console, and the <args> is an array of strings specified at the command line.


12. **Can you compile a Java class successfully without having the "main" method ?**


Yes, we can compile, but it won't run. 

The "main" method works as the startup function for a Java class, and the JVM calls it for the program execution


13. **Would a Java program compile/run if we use <static public void> instead of <public static void>?**


Yes, the program will compile and run as usual.


14. **What are the various access specifiers in Java ?**


1. Public - The classes, methods, or variables which are defined as public, can be accessed by any class or method.
2. Protected - Protected can be accessed by the class of the same package, or by the sub-class of this class, or within the same class.
3. Default - Default are accessible within the package only. By default, all the classes, methods, and variables are of default scope.
4 .Private - The private class, methods, or variables defined as private can be accessed within the class only.



15. **What do you understand of Garbage Collection and how to call it explicitly?**


If the object is no longer belong to any variable, Java automatically reclaims the memory. This process is known as garbage collection. 

You can use the <System.gc()> method to call it explicitly.  But it doesn’t ensure garbage collection then and there.



16. **How to compare the final, finally, and finalize keywords?**


Final– It's used to declare a constant. Final variable can’t be changed.
	Variables defined in an interface are implicitly final. You can’t 	extend a final class, means you can’t instantiate a final class

Finally– It makes you handle exceptions.
	It's a keyword used for exception handling. The code under the 	<finally> block gets executed apparently.

Finalize– It helps in garbage collection.
	The <finalize()> method is used just before an object is 	destroyed and garbage collected.



17. **Can a class be declared as static?**


We can’t declare a top level class as static, but the inner class can be declared as static



18. **When will you define a class a static?**


When a method needs to be access even before creating the object of the class, then we need to create the method with static identifier.

We can call a static method by: ClassName.StaticMethodName()


19. **What is classloader?**


Classloader is a subsystem of JVM which is used to load class files. Whenever we run the java program, it is loaded first by the classloader.

There are three built-in classloaders in Java.
Bootstrap ClassLoader.
Extension ClassLoader.
System/Application ClassLoader:


20. **Is Empty .java file name a valid source file name ?**


Yes, Java allows to save our java file by .java only, we need to compile it by javac .java and run by java classname


If you need this questions set. Kindly let me know in the comment section belong along with your email id.

See you on part 2 of this video
 Interview questions on
Core Java : OOPS Concept
