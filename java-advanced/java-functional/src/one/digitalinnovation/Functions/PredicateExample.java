package one.digitalinnovation.Functions;

import java.util.function.Predicate;

public class PredicateExample {

    public static void main(String[] args) {

        // Predicate receives a value and returns a boolean
        Predicate<String> isEmpty = String::isEmpty;
        System.out.println(isEmpty.test(""));
        System.out.println(isEmpty.test("May"));

    }


}
