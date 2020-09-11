package one.digitalinnovation.Threads;

import java.util.stream.IntStream;

public class ParallelStreamExample {

    public static void main(String[] args) {

        // Serial stream
        long start = System.currentTimeMillis();
        IntStream.range(1, 100000).forEach(num -> factorial(num));
        long finish = System.currentTimeMillis();
        System.out.println("Execution time (serial): " + (finish - start));

        // Parallel stream
        start = System.currentTimeMillis();
        IntStream.range(1, 100000).parallel().forEach(num -> factorial(num));
        finish = System.currentTimeMillis();
        System.out.println("Execution time (parallel): " + (finish - start));

    }

    public static long factorial(int num) {
        long fact = 1;

        for (int i = 2; i <= num; i++) {
            fact *= i;
        }

        return fact;
    }
}
