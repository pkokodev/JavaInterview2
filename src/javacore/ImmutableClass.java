package javacore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/*
 Always remember that your instance variables will be either mutable or immutable.
 Identify them and return new objects with copied content for all mutable objects.
 Immutable variables can be returned safely without extra effort.
 */
public final class ImmutableClass
{

    /*
     Integer class is immutable as it does not provide any setter to change its content
     */
    private final Integer immutableField1;

    /*
     String class is immutable as it also does not provide setter to change its content
     */
    private final String immutableField2;

    /*
     Date, Hashmap class is mutable as it provides setters to change various date/time parts
     */
    private final Date mutableField;

    /*
    When we have Collection like ArrayList or LinkedList as member variables, we should not use their in-built clone() method.
    Their clone() method returns shallow copy of this ArrayList instance.
    The elements themselves are not copied. So in such case, we can write our own method to deep copy ArrayList object.
    */
    private final ArrayList<String> list;


    //Default private constructor will ensure no unplanned construction of class
    private ImmutableClass(Integer fld1, String fld2, Date date, ArrayList<String> list)
    {
        this.immutableField1 = fld1;
        this.immutableField2 = fld2;
        this.mutableField = new Date(date.getTime());//must
        this.list = new ArrayList<>(list);//must Shallow copy
    }

    //Factory method to store object creation logic in single place
    public static ImmutableClass createNewInstance(Integer fld1, String fld2, Date date, ArrayList<String> list)
    {
        return new ImmutableClass(fld1, fld2, date, list);
    }

    //Provide no setter methods

    /*
     Integer class is immutable so we can return the instance variable as it is
     */
    public Integer getImmutableField1() {
        return immutableField1;
    }

    /*
     String class is also immutable so we can return the instance variable as it is
     */
    public String getImmutableField2() {
        return immutableField2;
    }

    /*
     Date class is mutable so we need a little care here.
     We should not return the reference of original instance variable.
     Instead a new Date object, with content copied to it, should be returned.
     */
    public Date getMutableField() {
        return new Date(mutableField.getTime());
    }

    /*
    clone() or new ArrayList<String>(list):

    Returns a shallow copy of this ArrayList instance. (The elements themselves are not copied.)

     When we have Collection like ArrayList or LinkedList as member variables, we should not use their in-built clone() method.
    Their clone() method returns shallow copy of this ArrayList instance.
    The elements themselves are not copied. So in such case, we can write our own method to deep copy ArrayList object.

    Collection framework also provides implementation of Unmodifiable Collection classes in Collections utility class.
    These Unmodifiable collections are just a wrapper around normal Collection classes which throws UnsupportedOperationException from
    all the methods which tries to modify Collection i.e. add(), remove()
    */
    public ArrayList<String> getList() {
         return (ArrayList<String>) Collections.unmodifiableCollection(list);
    }

    @Override
    public String toString() {
        return immutableField1 +" - "+ immutableField2 +" - "+ mutableField;
    }
}