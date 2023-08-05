import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        String input = "baeldung,tutorial,splitting,text,\"ignoring this comma,\"";
        String[] tokens = input.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        Arrays.stream(tokens).forEach(t -> System.out.println(t));
        Helper h = null;
        int var = h.var;
        System.out.println(var);
    }
}

class Helper {
    static int var = 100;
}