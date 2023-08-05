Contents (Level - Basic)
 TOP 20 interview questions
OOPs CONCEPT OF JAVA
TARGETED COMPANIES
PART - 2

**1. What is Object-Oriented Programming?**

Object-Oriented programming (OOP) refers to a type of programming in which programmers define the data type of a data structure and the type of operations that can be applied to the data structure.

OOPs is a programming paradigm that includes or relies on the concept of classes and objects. It is used to structure a software program into simple, reusable pieces of code (usually called classes) which are used to create individual instances of objects.

Why we use OOPS ? OOP makes code organized, reusable, and easy to maintain; It follows the “Don’t Repeat Yourself” method. Benefits of OOP include security; OOP prevents unwanted access to data, or exposing proprietary code through encapsulation and abstraction 


**2. What are the benefits of Object Oriented Programming?**

Improved productivity during software development 
Improved software maintainability
Faster development sprints
Lower cost of development
Higher quality software


**3. What are the main features of OOPs?**

Abstraction
Encapsulation
Inheritance
Polymorphism

					


**4. What is Abstraction?**

Principle of abstraction, only deals with essential behaviour of an object. It deals with “What an Object can do?” rather than “How it is going to do?”. i.e. it ignores the internal implementation details.
Abstraction means that the user interacts with only selected attributes and methods of an object. Abstraction uses simplified, high level tools, to access a complex object.							


**5. What is Encapsulation ?**

Encapsulation means, containing all important information inside an object, and only exposing selected information to the outside world. Attributes and behaviours are defined by code inside the class template. Then, when an object is instantiated from the class, the data and methods are encapsulated in that object. Encapsulation hides the internal software code implementation inside a class, and hides internal data of inside objects. 

It is a mechanism where you bind your data and code together as a single unit. It also means to hide your data in order to make it safe from any modification.


**6. What is Inheritance ?**

Inheritance can be defined as the process where one class acquires the properties (methods and fields) of another. With the use of inheritance the information is made manageable in a hierarchical order. The class which inherits the properties of other is known as subclass (derived class, child class) and the class whose properties are inherited is known as superclass (base class, parent class)

A child or a derived class can inherit the parent or base class using the “extends” keyword. There are 3 types of inheritance:
Single inheritance
Multiple inheritance
Multi Level inheritance

Java supports multilevel inheritance, not multiple inheritance 

**More detailed questions in later videos 	       

**7. What is Polymorphism ?**
The word polymorphism means having many forms. In other words, it refers to the ability of a variable, function or object to take on multiple forms

In Java polymorphism is mainly divided into two types:
a. Compile time Polymorphism or static polymorphism
		Example: Method Overloading

b. Runtime Polymorphism or dynamic polymorphism
		Example: Method Overriding




**More detailed questions in later video****s

**8. What is a class and an object?**
Class : 
A class is a prototype that consists of objects in different states and with different behaviours. It has a number of methods that are common the objects present within that class. In simple words, a class can be defined as a blueprint of any object.

Object:
Object is instance of class. An object is a real-world entity which is the basic unit of OOPs for example chair, cat, dog, etc. Different objects have different states or attributes, and behaviours.

**9. Why Java does not support multiple inheritance?**

Multiple inheritance is not supported because it leads to deadly diamond problem. However, it can be solved but it leads to complex system so multiple inheritance has been dropped by Java founders.

But, Java supports multiple inheritance through interfaces only. A class can implement any number of interfaces but can extend only one class

**10. What is method overloading in Java?**

Method overloading is a feature of OOPs which makes it possible to give the same name to more than one methods within a class if the arguments passed differ.

Class Example {
public void method() {}
public void method(int a){}
public void method(int a, int b){}
}

Method overloading cannot be achieved by changing the return type of the method.
In overloading, it is must that both methods should have −
	a. Same name.
	b. Different parameters (different type or, different number or both).


**11. What is method overriding in Java?**

Method overriding is one of the way by which java achieve Runtime polymorphism. The version of a method that is executed will be determined by the object that is used to invoke it. If an object of a parent class is used to invoke the method, then the version in the parent class will be executed, but if an object of the subclass is used to invoke the method, then the version in the child class will be executed. 

Class Parent {
method1(){} 		//overriden method
method2(){}
}

class Child extends Parent {
method1(){} 		//overridding method
method3(){}}

**12. What is dynamic method displatch?**

Dynamic method dispatch is one of the ways in which Java supports Runtime Polymorphism. Dynamic method dispatch is the mechanism by which a call to an overridden method is resolved at run time, rather than compile time.

Class Parent {
method1(){}
method2(){}
}

class Child extends Parent {
method1(){} 
method3(){}}
Parent p=new Parent();
p.method1() 	 //calling parent method1

Parent c=new Child();
c.method1() 	//calling child method1

**13. What are the rules of method overloading and overriding in Java?**

One of the most important rules of method overloading in Java is that the method signature should be different i.e. either the number of arguments or the type of arguments. Simply changing the return type of two methods will not result in overloading, instead, the compiler will throw an error. 

On the other hand, method overriding has more rules e.g. name and return type must be the same, method signature should also be the same, the overloaded method cannot throw a higher exception, etc


**14. Can we overload / override a static method in Java?**

Overload: Yes, you can overload a static method in Java. You can declare as many static methods with same name as you wish provided all of them have different method signatures, which satisfies the condition of overloading.
Override: No, you cannot override a static method because it's not a part to an object. Instead, static methods belong to a class and resolved at compile time using the type of reference variable. But, you can declare the same static method in a subclass, that will result in method hiding


**15. What is covariant return types in java?**
Before JDK 5.0, it was not possible to override a method by changing the return type. When we override a parent class method, the name, argument types and return type of the overriding method in child class has to be exactly same as that of parent class method.

Java 5.0 onwards it is possible to have different return type for a overriding method in child class, but child’s return type should be sub-type of parent’s return type. Overriding method becomes variant with respect to return type.


**16. How can you prevent a method from overriding?**

There are several ways by which you can prevent a method from overriding. For example. 
You can declare a method as final to prevent it from getting overridden.
You can make the method as private or static, to prevent it form getting overridden.


**17. What is an abstract class?**
An abstract class is a class that is incomplete. You cannot create an instance of an abstract class in Java. They are provided to define default behaviour and ensured that client of that class should adore to those contract which is defined inside the abstract class.
In order to use it, you must extend and implement their abstract methods. In Java, a class can be abstract without specifying any abstract method.


**18. What is an interface in java?**

An abstract class is a class that is incomplete. You cannot create an instance of an abstract class in Java. They are provided to define default behaviour and ensured that client of that class should adore to those contract which is defined inside the abstract class.
In order to use it, you must extend and implement their abstract methods. In Java, a class can be abstract without specifying any abstract method.


**19. What is an difference between abstract class and interface in java?**

In Java, the key difference between abstract class and interface is that, abstract class can contain a non-abstract method but the interface cannot, but from Java 8 onward interface can also contain static and default methods that are non-abstract.



**20. Can we declare an interface as final?**

No, we cannot declare an interface as final because the interface must be implemented by some class to provide its definition. Therefore, there is no sense to make an interface final. 

However, if you try to do so, the compiler will show compile time error.


If you need this questions set. Kindly let me know in the comment section belong along with your email id.

See you on part 3 of this video
 Interview questions on
Java Constructor

**Association Aggregation Composition**

For two objects, Foo and Bar the relationships can be defined

**Association** - I have a relationship with an object. Foo uses Bar

	public class Foo {         
	private Bar bar;
	};

NB: See Fowler's definition - the key is that Bar is semantically related to Foo rather than just a dependency (like an int or string).

**Composition** - I own an object and I am responsible for its lifetime. When Foo dies, so does Bar

	public class Foo {
	private Bar bar = new Bar();
	}

**Aggregation** - I have an object which I've borrowed from someone else. When Foo dies, Bar may live on.

	public class Foo {
	private Bar bar;
	Foo(Bar bar) {
	this.bar = bar;
	}
	}

Inheritance --> Student is a User
Association --> Student has a Department
Aggregation --> Student has a List Of enrolled courses.
Composition --> Student has a Scorecard