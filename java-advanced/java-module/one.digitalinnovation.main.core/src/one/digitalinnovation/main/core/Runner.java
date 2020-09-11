package one.digitalinnovation.main.core;

import one.digitalinnovation.utils.operation.Calculator;

public class Runner {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.sum(1, 2));
    }
}
