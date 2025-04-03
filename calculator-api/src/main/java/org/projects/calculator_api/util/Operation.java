package org.projects.calculator_api.util;

public class Operation {
    public static double operation(String a, String b, String operation) {
        double num1 = Double.parseDouble(a);
        double num2 = Double.parseDouble(b);
        return switch (operation) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> {
                if (num2 == 0) {
                    throw new ArithmeticException("Ошибка: деление на ноль!");
                }
                yield num1 / num2;
            }
            default -> throw new IllegalArgumentException("Неизвестная арифметическая выражения: " + operation);
        };
    }
}
