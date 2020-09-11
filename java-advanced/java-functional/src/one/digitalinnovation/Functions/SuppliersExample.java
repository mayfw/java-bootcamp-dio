package one.digitalinnovation.Functions;

import java.util.function.Supplier;

public class SuppliersExample {

    public static void main(String[] args) {

        // Suppliers don`t receive parameters and return something
        Supplier<Person> supplier = () -> new Person();
        System.out.println(supplier.get());
    }
}

class Person {
    private String name;
    private int age;

    public Person() {
        name = "May";
        age = 30;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Age: %d", name, age);
    }
}