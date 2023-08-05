package javacore.inheritance;

public interface IManager {
    void getWorkInfo();
    default void login(){
        System.out.println("login called");
    }
}
