package java8.streams;


import common.Employee;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class B_QuestionSet {
    static List<Employee> employeeList = Employee.getEmployees();

    public static void main(String[] args) {

        // Query 1 : Top 3 highest paid employees.
        method19();

        // Query 1 : How many male and female employees are there in the organization?
        //method1();

        // Query 2 : Print the name of all departments in the organization?
        //method2();

        // Query 3 : What is the average age of male and female employees?
        //method3();

        // Query 4 : Get the details of highest paid employee in the organization?
        //method4();

        // Query 5 : Get the names of all employees who have joined after 2015?
        //method5();

        // Query 6 : Count the number of employees in each department?
        //method6();

        // Query 7 : What is the average salary of each department?
        //method7();

        // Query 8 : Get the details of youngest male employee in the product
        // development department?
        //method8();

        // Query 9 : Who has the most working experience in the organization?
        //method9();

        // Query 10 : How many male and female employees are there in the sales and
        // marketing team?
        //method10();

        // Query 11 : What is the average salary of male and female employees?
        //method11();

        // Query 12 : List down the names of all employees in each department?
        //method12();

        // Query 13 : What is the average salary and total salary of the whole organization?
        //method13();

        // Query 14 : Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
        //method14();

        // Query 15 : Who is the oldest employee in the organization? What is his age
        // and which department he belongs to?
        //method15();

        // Query 16 : Highest paid employee from each department.
        //method16();

        // Query 17 : Find the frequency of string chacracters
        //method17();

        // Query 18 : map() vs flatMap() List all employees phone numbers
        //method18();
    }

    //Query 1 : Top 3 highest paid employees.
    private static void method19() {
        List<String> list = employeeList.stream()
                .sorted((e1, e2) -> (int) (e2.getSalary() - e1.getSalary()))
                .map(e -> e.getName() + "  " +e.getSalary())
                .limit(3)
                .toList();
        System.out.println(list);

        //Second highest
        Optional<Employee> emp = employeeList.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).skip(1).findFirst();

    }
    // Query 18 : map() vs flatMap()
    private static void method18() {
        employeeList.get(0).setPhoneNumbers(Arrays.asList("9860214578", "44612139"));
        employeeList.get(1).setPhoneNumbers(Arrays.asList("8602145568", "9446121394", "78456896"));

        //Using map getting List<List<String>>

        List<List<String>> allEmpPhoneNumbers = employeeList
                .stream()
                .filter(e -> e.getPhoneNumbers() != null)
                .map(e -> e.getPhoneNumbers()).toList();

        System.out.println(allEmpPhoneNumbers);//[[9860214578, 44612139], [8602145568, 9446121394, 78456896]]

        //Using flatMap getting List<String> --> Stream flatMap(Function)

        List<String> allEmpPhoneNumbers1 = employeeList
                .stream()
                .filter(e -> e.getPhoneNumbers() != null)
                .flatMap(e -> e.getPhoneNumbers().stream()).toList();

        System.out.println(allEmpPhoneNumbers1);//[9860214578, 44612139, 8602145568, 9446121394, 78456896]
    }

    private static void method17() {
        String s = "evnovneoivne";

        Stream<String> strs = Stream.of(s.split(""));
        Map<String, Long> map = strs.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(map);
    }

    private static void method16() {
        Map<String, Optional<Employee>> map = employeeList
                .stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment(), Collectors.maxBy(Comparator.comparingDouble(e -> e.getSalary()))));
        System.out.println(map);
    }

    // Query 15 : Who is the oldest employee in the organization? What is his age and which department he belongs to?
    private static void method15() {
        Optional<Employee> optionalEmployee = employeeList
                .stream()
                .min((e1, e2) -> e1.getAge() - e2.getAge());//Id : 255, Name : Ali Baig, age : 23, Gender : Male, Department : Infrastructure, Year Of Joining : 2018, Salary : 12700.0
        System.out.println(optionalEmployee.get().toString());

        Optional<Employee> optionalEmployee1 = employeeList
                .stream()
                .max((e1, e2) -> e1.getAge() - e2.getAge());//Id : 166, Name : Iqbal Hussain, age : 43, Gender : Male, Department : Security And Transport, Year Of Joining : 2016, Salary : 10500.0
        System.out.println(optionalEmployee1.get().toString());

        //Here both min max having same comparator as argument but the result is different or Comparator.comparingInt(Employee::getAge)
    }

    // Query 14 : Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
    private static void method14() {
        Map<Boolean, List<Employee>> map = employeeList
                .stream()
                .collect(Collectors.partitioningBy(e -> e.getAge() <= 25));
        System.out.println(map);//{false=[age>25], true=[age<=25]}
    }

    // Query 13 : What is the average salary and total salary of the whole organization?
    private static void method13() {
        DoubleSummaryStatistics doubleSummaryStatistics = employeeList
                .stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("Total Organization Salary: " + doubleSummaryStatistics.getSum());
        System.out.println("Average Salary: " + doubleSummaryStatistics.getAverage());
        System.out.println("Count Salary: " + doubleSummaryStatistics.getCount());
        System.out.println("Min Salary: " + doubleSummaryStatistics.getMin());
        System.out.println("Max Salary: " + doubleSummaryStatistics.getMax());
    }

    // Query 12 : List down the names of all employees in each department?
    private static void method12() {
        Map<String, List<String>> map = employeeList
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.mapping(Employee::getName, Collectors.toList())));

        System.out.println(map);//{Department=[Employee Names]}
    }

    // Query 11 : What is the average salary of male and female employees?
    private static void method11() {
        Map<String, Double> map = employeeList
                .stream()
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(map);//{Male=21300.090909090908, Female=20850.0}
    }

    // Query 10 : How many male and female employees are there in the sales and marketing team?
    private static void method10() {
        Map<String, Long> map = employeeList
                .stream()
                .filter(e -> e.getDepartment().equals("Sales And Marketing"))
                .collect(Collectors.groupingBy(Employee::getGender, Collectors.counting()));
        System.out.println(map);//{Female=1, Male=2}
    }

    // Query 9 : Who has the most working experience in the organization?
    private static void method9() {
        Optional<Employee> optionalEmployee = employeeList
                .stream()
                .min((e1, e2) -> e1.getYearOfJoining() - e2.getYearOfJoining());
        //Comparator.comparingInt(Employee::getYearOfJoining) Also return same comparator like above one
        System.out.println(optionalEmployee.get().getName());

        //Or Using sort

        Optional<Employee> optionalEmployee1 = employeeList
                .stream()
                .sorted((e1, e2) -> e1.getYearOfJoining() - e2.getYearOfJoining())
                .findFirst();
        System.out.println(optionalEmployee1.get().getName());
    }

    // Query 8 : Get the details of youngest male employee in the product development department?
    private static void method8() {
        Optional<Employee> optionalEmployee = employeeList
                .stream()
                .filter(e -> e.getDepartment().equals("Product Development"))
                .min((e1, e2) -> e1.getAge() - e2.getAge());// Nitin Joshi --> first - second --> ascending
        //.min(Comparator.comparingInt(Employee::getAge)); //Or
        System.out.println(optionalEmployee.get().getName());
    }

    // Query 7 : What is the average salary of each department?
    private static void method7() {
        Map<String, Double> map = employeeList
                .stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment(), Collectors.averagingDouble(e -> e.getSalary())));
        System.out.println(map);//{Product Development=31960.0, Security And Transport=10750.25, Sales And Marketing=11900.166666666666, Infrastructure=15466.666666666666, HR=23850.0, Account And Finance=24150.0}
    }

    // Query 6 : Count the number of employees in each department?
    private static void method6() {
        Map<String, Long> map = employeeList
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
        System.out.println(map);//{Product Development=5, Security And Transport=2, Sales And Marketing=3, Infrastructure=3, HR=2, Account And Finance=2}
    }

    // Query 5 : Get the names of all employees who have joined after 2015?
    private static void method5() {
        List<Employee> employees = employeeList
                .stream()
                .filter(e -> e.getYearOfJoining() > 2015)
                .collect(Collectors.toList());
        System.out.println(employees);

        //Or

        List<Employee> employees1 = employeeList
                .stream()
                .collect(Collectors.filtering(e -> e.getYearOfJoining() > 2015, Collectors.toList()));
        System.out.println(employees1);
    }

    // Query 4 : Get the details of highest paid employee in the organization?
    private static void method4() {
        Optional<Employee> optionalEmployee = employeeList
                .stream()
                .max((e1, e2) -> (int) (e1.getSalary() - e2.getSalary()));
        System.out.println(optionalEmployee.get().getName() + optionalEmployee.get().getSalary());//Anuj Chettiar35700.0

        //Or Above is Nice my way

        Optional<Employee> optionalEmployee1 = employeeList
                .stream()
                .collect(Collectors.maxBy((e1, e2) -> (int) (e1.getSalary() - e2.getSalary())));
        System.out.println(optionalEmployee1.get().getName() + optionalEmployee1.get().getSalary());//Anuj Chettiar35700.0

    }

    // Query 3 : What is the average age of male and female employees?
    private static void method3() {
        Map<String, Double> map = employeeList
                .stream()
                .collect(
                        Collectors.groupingBy(Employee::getGender, Collectors.averagingInt(Employee::getAge))
                );
        System.out.println(map);//{Male=30.181818181818183, Female=27.166666666666668}
    }

    // Query 2 : Print the name of all departments in the organization?
    private static void method2() {
        Set<String> set = employeeList
                .stream()
                .map(e -> e.getDepartment())
                .collect(Collectors.toSet());
        System.out.println(set);//[Product Development, Sales And Marketing, Security And Transport, Infrastructure, HR, Account And Finance]

        //Or

        employeeList
                .stream()
                .map(e -> e.getDepartment())
                .distinct()
                .forEach(d -> System.out.println(d));
    }

    // Query 1 : How many male and female employees are there in the organization?
    private static void method1() {
        Map<String, Long> map = employeeList
                .stream()
                .collect(
                        Collectors
                                .groupingBy(e -> e.getGender(), Collectors.counting())
                );
        System.out.println(map);// {Male=11, Female=6}

        Map<String, List<Employee>> map1 = employeeList
                .stream()
                .collect(
                        Collectors
                                .groupingBy(e -> e.getGender())
                );
        System.out.println(map1);// {Male=List, Female=List}

        Map<String, Map<Integer, List<Employee>>> map2 = employeeList
                .stream()
                .collect(
                        Collectors
                                .groupingBy(e -> e.getGender(), Collectors.groupingBy(e -> e.getAge()))
                );
        System.out.println(map2);// {Male={Age=List}, Female={Age=List}}
    }
}
