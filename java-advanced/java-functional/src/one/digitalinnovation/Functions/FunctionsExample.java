package one.digitalinnovation.Functions;

import java.util.function.Function;

public class FunctionsExample {

    public static void main(String[] args) {

        Function<String, String> returnNameBackwards = text -> new StringBuilder(text).reverse().toString();
        System.out.println(returnNameBackwards.apply("May Wong"));

        Function<String, Integer> convertStringToInt = Integer::valueOf;
        System.out.println(convertStringToInt.apply("30"));
    }
}
