package javacore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImmutableList {
    public static void main(String[] args) {
        //testShallow();
        testDeepCopy();
    }

    private static void testDeepCopy() {
        Employee employee1 = new Employee(1, "A");
        Employee employee2 = new Employee(2, "B");
        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        IMMU immu = new IMMU(employees);
        System.out.println(employees);
        employees.stream().forEach(e -> System.out.println(e.name + " " + e.id));
        System.out.println(immu.getEmployeeList());
        immu.getEmployeeList().stream().forEach(e -> System.out.println(e.name + " " + e.id));
        System.out.println("===========================");
        immu.getEmployeeList().remove(0);//java.lang.UnsupportedOperationException
        immu.getEmployeeList().get(0).id = 3;// But this can be work

        System.out.println(employees);
        employees.stream().forEach(e -> System.out.println(e.name + " " + e.id));
        System.out.println(immu.getEmployeeList());
        immu.getEmployeeList().stream().forEach(e -> System.out.println(e.name + " " + e.id));
    }

    private static void testShallow() {
        Employee employee1 = new Employee(1, "A");
        Employee employee2 = new Employee(2, "B");
        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        IMMU immu = new IMMU(employees);
        System.out.println(employees);
        employees.stream().forEach(e -> System.out.println(e.name + " " + e.id));
        System.out.println(immu.getEmployeeList());
        immu.getEmployeeList().stream().forEach(e -> System.out.println(e.name + " " + e.id));
        System.out.println("===========================");
        //immu.getEmployeeList().remove(0);//java.lang.UnsupportedOperationException
        immu.getEmployeeList().get(0).id = 3;// But this can be work

        System.out.println(employees);
        employees.stream().forEach(e -> System.out.println(e.name + " " + e.id));
        System.out.println(immu.getEmployeeList());
        immu.getEmployeeList().stream().forEach(e -> System.out.println(e.name + " " + e.id));

        /*

[javacore.Employee@4dd8dc3, javacore.Employee@6d03e736]
A 1
B 2
[javacore.Employee@4dd8dc3, javacore.Employee@6d03e736]
A 1
B 2
===========================
[javacore.Employee@4dd8dc3, javacore.Employee@6d03e736]
A 3
B 2
[javacore.Employee@4dd8dc3, javacore.Employee@6d03e736]
A 3
B 2

        */
    }
}

final class IMMU {
    private final List<Employee> employeeList;


    public IMMU(List<Employee> employeeList) {
        this.employeeList = new ArrayList<>(employeeList);
    }

    public List<Employee> getEmployeeList() {
        //Deep Copy
        ArrayList<Employee> list = new ArrayList<>();
        employeeList.stream()
                .forEach(e -> list.add(new Employee(e.id, e.name)));
        return list;

        //Objects in list can change
        //return Collections.unmodifiableList(employeeList);
    }

    @Override
    public String toString() {
        return "IMMU{" +
                "employeeList=" + employeeList +
                '}';
    }
}