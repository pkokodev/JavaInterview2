package javacore.inheritance;

public class Employee implements IUser, IManager{
    public static void main(String[] args) {
        Employee emp = new Employee();
        emp.login();
        emp.getWorkInfo();

    }


    @Override
    public void getWorkInfo() {

    }
}
