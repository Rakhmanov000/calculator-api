package org.projects.calculator_api.service;

import org.projects.calculator_api.util.Calculator;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    private final Calculator calculator;

    // В конструкторе создаем объект Calculator
    public CalculatorService() {
        this.calculator = new Calculator();
    }

    // Метод для вычисления результата
    public double calculate(String expression) {
        return calculator.calculate(expression);
    }
}
