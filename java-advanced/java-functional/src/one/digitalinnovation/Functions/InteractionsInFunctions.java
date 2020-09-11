package one.digitalinnovation.Functions;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InteractionsInFunctions {

    public static void main(String[] args) {

        String[] names = {"May", "Wong", "Studying", "Java"};
        Integer[] numbers = {1, 2, 3, 4, 5};

        printFilteredNamesUsingFor(names);
        printFilteredNames(names);
        printAllNamesUsingFor(names);
        printAllNames(names);

        printTheDoubleOfTheNumbersUsingFor(numbers);
        printTheDoubleOfTheNumbers(numbers);

    }

    public static void printFilteredNamesUsingFor(String... names) {

        String printNames = "";

        for (int i = 0; i < names.length; i++) {
            if (names[i].equals("May")) {
                printNames += "" + names[i];
            }
        }

        System.out.println(printNames);
    }

    public static void printFilteredNames(String... names) {

        String printNames = Stream.of(names).filter(name -> name.equals("May"))
                .collect(Collectors.joining());

        System.out.println(printNames);
    }

    public static void printAllNamesUsingFor(String... names) {
        for (String name : names) {
            System.out.println(name);
        }
    }

    public static void printAllNames(String... names) {
        Stream.of(names).forEach(System.out::println);
    }

    public static void printTheDoubleOfTheNumbersUsingFor(Integer... numbers) {
        for (Integer number : numbers) {
            System.out.println(number * 2);
        }
    }

    public static void printTheDoubleOfTheNumbers(Integer... numbers) {
        Stream.of(numbers).map(number -> number * 2).forEach(System.out::println);
    }
}
