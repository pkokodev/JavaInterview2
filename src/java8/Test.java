package java8;

public class Test {
    public static void main(String[] args) {
        C1 c1 = new C1();
        c1.getGreeting();
    }
}
 interface I1 {
    default String getGreeting() {
        return "Good Morning!";
    }
}

 interface I2 {
    default String getGreeting() {
        return "Good Night!";
    }
}

 class C1 implements I1, I2 {
    @Override
    public String getGreeting() {
        //return I1.super.getGreeting();
        return "System.out.println()";
        //return I1.super.getGreeting();
    }
}