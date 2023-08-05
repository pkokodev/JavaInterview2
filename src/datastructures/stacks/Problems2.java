package datastructures.stacks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/*
Infix expressions are readable and solvable by humans. We can easily distinguish the order of operators,
and also can use the parenthesis to solve that part first during solving mathematical expressions.
The computer cannot differentiate the operators and parenthesis easily, that’s why postfix conversion is needed.
Hence, Compiler convert infix to postfix for faster computation.

infixTOPostFix -> push only operators in the stack, pop if ) or less precedence operator or same precedence(bcz associativity)
postfix evaluation -> push only operands in the stack, pop if operator comes, first pop is 2nd operand push the result again into the stack

*/
public class Problems2 {
    public static void main(String[] args) {
        //String exp = "a+b*(c^d-e)^(f+g*h)-i";
        //System.out.println(infixTOPostFix(exp));//abcd^e-fgh*+^*+i-

        //String exp="231*+9-";
        //System.out.println(postfixEvaluation(exp));//-4
    }

    private static int postfixEvaluation(String exp) {
        //create a stack
        Stack<Integer> stack=new Stack<>();

        // Scan all characters one by one
        for(int i=0;i<exp.length();i++)
        {
            char c=exp.charAt(i);

            // If the scanned character is an operand (number here),
            // push it to the stack.
            if(Character.isDigit(c))
                stack.push(c - '0');

                //  If the scanned character is an operator, pop two
                // elements from stack apply the operator
            else
            {
                int val1 = stack.pop();
                int val2 = stack.pop();

                switch(c)
                {
                    case '+':
                        stack.push(val2+val1);
                        break;

                    case '-':
                        stack.push(val2- val1);
                        break;

                    case '/':
                        stack.push(val2/val1);
                        break;

                    case '*':
                        stack.push(val2*val1);
                        break;
                }
            }
        }
        return stack.pop();
    }

    private static String infixTOPostFix(String exp) {
        // initializing empty String for result
        String result = new String("");

        // initializing empty stack
        Deque<Character> stack
                = new ArrayDeque<Character>();

        for (int i = 0; i < exp.length(); ++i) {
            char c = exp.charAt(i);

            // If the scanned character is an
            // operand, add it to output.
            if (Character.isLetterOrDigit(c))
                result += c;

                // If the scanned character is an '(',
                // push it to the stack.
            else if (c == '(')
                stack.push(c);

                //  If the scanned character is an ')',
                // pop and output from the stack
                // until an '(' is encountered.
            else if (c == ')') {
                while (!stack.isEmpty()
                        && stack.peek() != '(') {
                    result += stack.peek();
                    stack.pop();
                }

                stack.pop();
            }
            else // an operator is encountered
            {
                while (!stack.isEmpty()
                        && Prec(c) <= Prec(stack.peek())) {

                    result += stack.peek();
                    stack.pop();
                }
                stack.push(c);
            }
        }

        // pop all the operators from the stack
        while (!stack.isEmpty()) {
            if (stack.peek() == '(')
                return "Invalid Expression";
            result += stack.peek();
            stack.pop();
        }

        return result;
    }

    // A utility function to return
    // precedence of a given operator
    // Higher returned value means
    // higher precedence
    static int Prec(char ch)
    {
        switch (ch) {
            case '+':
            case '-':
                return 1;

            case '*':
            case '/':
                return 2;

            case '^':
                return 3;
        }
        return -1;
    }
}
