package one.digitalinnovation.Functions;

import java.util.function.Consumer;

public class ConsumerExample {

    public static void main(String[] args) {
        Consumer<String> printPhrase = System.out::println;
        printPhrase.accept("Hello World!");

        Consumer<String> printPhrase2 = phrase -> System.out.println(phrase);
        printPhrase2.accept("Hello World!");
    }
}
