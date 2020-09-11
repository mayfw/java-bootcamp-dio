package one.digitalinnovation.Functions;

import java.util.Arrays;

public class FunctionalAndImperativeExample {

    public static void main(String[] args) {

        int[] values = {1, 2, 3, 4};

        // Functional programming example

        Arrays.stream(values)
                .filter(number -> number % 2 == 0)
                .map(number -> number * 2)
                .forEach(number -> System.out.println(number));

        // The same functional program, but in Imperative form

        for (int i = 0; i < values.length; i++) {
            int value = 0;
            if (values[i] % 2 == 0) {
                value = values[i] * 2;

                if (value != 0) {
                    System.out.println(value);
                }
            }
        }
    }

}
