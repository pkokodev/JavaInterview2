package designpatterns;

import java.util.ArrayList;
import java.util.List;

/*


"Shallow copies" duplicate as little as possible. A shallow copy of a collection is a copy of the collection structure, not the elements.
With a shallow copy, two collections now share the individual elements.

"Deep copies" duplicate everything. A deep copy of a collection is two collections with all of the elements in the original collection duplicated.

Shallow Cloning:
Definition: "A shallow copy of an object copies the ‘main’ object, but doesn’t copy the inner objects."
When a custom object (eg. Employee) has just primitive, String type variables then you use Shallow Cloning.

Employee e = new Employee(2, "john cena");
Employee e2=e.clone();
You return super.clone(); in the overridden clone() method and your job is over.

Deep Cloning:
Definition: "Unlike the shallow copy, a deep copy is a fully independent copy of an object."
Means when an Employee object holds another custom object:

Employee e = new Employee(2, "john cena", new Address(12, "West Newbury", "Massachusetts");



The implementation of clone() in Object checks if the actual class implements Cloneable, and creates an instance of that actual class.

So if you want to make your class cloneable, you have to implement Cloneable and downcast the result of super.clone() to your class.
Another burden is that the call to super.clone() can throw a CloneNotSupportedException that you have to catch, even though you know
it won't happen (since your class implements Cloneable).

The Cloneable interface and the clone method on the Object class are an obvious case of object-oriented design gone wrong.

*/
public class D_PrototypeDp {
    public static void main(String[] args) throws CloneNotSupportedException {
        Vehicle a = new Vehicle();
        a.insertData();

        Vehicle b = (Vehicle) a.clone();
        List<String> list = b.getVehicleList();
        list.add("Honda new Amaze");

        System.out.println(a.getVehicleList());
        System.out.println(list);

        b.getVehicleList().remove("Audi A4");
        System.out.println(list);
        System.out.println(a.getVehicleList());
    }

}



class Vehicle implements Cloneable {
    private List<String> vehicleList;

    public Vehicle() {
        this.vehicleList = new ArrayList<String>();
    }

    public Vehicle(List<String> list) {
        this.vehicleList = list;
    }

    public void insertData() {
        vehicleList.add("Honda amaze");
        vehicleList.add("Audi A4");
        vehicleList.add("Hyundai Creta");
        vehicleList.add("Maruti Baleno");
        vehicleList.add("Renault Duster");
    }

    public List<String> getVehicleList() {
        return this.vehicleList;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        List<String> tempList = new ArrayList<String>();

        for(String s : this.getVehicleList()) {
            tempList.add(s);
        }

        return new Vehicle(tempList);
    }
}
