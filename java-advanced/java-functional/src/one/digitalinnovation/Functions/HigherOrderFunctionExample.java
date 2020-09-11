package one.digitalinnovation.Functions;

/*
 Higher order function is a function which returns a function
 or receives a function as parameter
*/

public class HigherOrderFunctionExample {

    public static void main(String[] args) {
        Calculation add = (a, b) -> a + b;
        Calculation sub = (a, b) -> a - b;
        Calculation mult = (a, b) -> a * b;
        Calculation div = (a, b) -> a / b;

        System.out.println(executeCalculus(add, 1, 3));
        System.out.println(executeCalculus(sub, 5, 3));
        System.out.println(executeCalculus(mult, 2, 5));
        System.out.println(executeCalculus(div, 10, 2));

    }

    // Higher order function
    public static int executeCalculus(Calculation calculation, int a, int b) {
        return calculation.calculus(a, b);
    }

}

// Needed interface for Lambda
interface Calculation {
    public int calculus(int a, int b);
}
