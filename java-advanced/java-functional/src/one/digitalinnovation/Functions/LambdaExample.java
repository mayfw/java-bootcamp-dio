package one.digitalinnovation.Functions;

public class LambdaExample {

    public static void main(String[] args) {

        // Lambda: FunctionalInterface variableName = parameter -> logic
        // Example: Birthday happyBirthday = name -> "Happy Birthday " + name;

        Birthday happyBirthday = name -> "Happy Birthday " + name;
        System.out.println(happyBirthday.congrats("May"));

    }
}

// Interface needed for Lambda, with only one abstract method
@FunctionalInterface
interface Birthday {
    String congrats(String name);
}
