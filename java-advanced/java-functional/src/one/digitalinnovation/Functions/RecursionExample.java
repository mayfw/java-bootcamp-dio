package one.digitalinnovation.Functions;

import java.net.Inet4Address;
import java.util.HashMap;
import java.util.Map;

public class RecursionExample {

    public static void main(String[] args) {
        System.out.println("Recursive Factorial: " + factorial(5));
        System.out.println("Factorial with Tail Call: " + factorialTC(5));

        // Memoization tests
        // testing how much time will get to calculate this factorial
        long I = System.nanoTime();
        System.out.println(factorialMemorization(15));
        long F = System.nanoTime();
        System.out.println("FACTORIAL 1 " + (F - I));

        // testing again how much time will get after factorial of 15 is cached on memory
        I = System.nanoTime();
        System.out.println(factorialMemorization(15));
        F = System.nanoTime();
        System.out.println("FACTORIAL 2 " + (F - I));

    }

    // Recursive Factorial (high processing cost)
    // (factorial(5))
    // (5 * (factorial(4)))
    // (5 * (4 * (factorial(3))))
    // (5 * (4 * (3 * (factorial(2)))))
    // (5 * (4 * (3 * (2 * (factorial(1))))))
    // (5 * (4 * (3 * (2 * (1 * (factorial(0)))))))
    // (5 * (4 * (3 * (2 * (1 * (1))))))
    // (5 * (4 * (3 * (2 * (1)))))
    // (5 * (4 * (3 * (2))))
    // (5 * (4 * (6)))
    // (5 * (24))
    // 120

    public static int factorial(int value) {
        if (value == 1) {
            return value;
        } else {
            return value * factorial(value - 1);
        }

    }

    // Factorial with Tail Call (faster, as it stack less calls, but it is not supported by JVM)
    // factorialTC(5, 1)
    // factorialTC(4, 5)
    // factorialTC(3, 20)
    // factorialTC(2, 60)
    // factorialTC(1, 120)
    // factorialTC(0, 120)

    public static int factorialTC(int value) {
        return factorialTailCall(value, 1);
    }

    public static int factorialTailCall(int value, int number) {
        if (value == 0) {
            return number;
        }
        return factorialTailCall(value - 1, number * value);
    }

    // Factorial with Memoization (faster, but cost more memory)

    static Map<Integer, Integer> FACTORIAL_MAP = new HashMap<>();

    public static Integer factorialMemorization(Integer value) {
        if (value == 1) {
            return value;
        } else {
            if (FACTORIAL_MAP.containsKey(value)) {
                return FACTORIAL_MAP.get(value);
            } else {
                Integer result = value * factorialMemorization(value - 1);
                FACTORIAL_MAP.put(value, result);
                return result;
            }
        }
    }
}
