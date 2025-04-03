package org.projects.calculator_api.util;

import org.springframework.stereotype.Component;

@Component
public class Calculator {
    private final ExpressionParser expressionParser;

    public Calculator() {
        this.expressionParser = new ExpressionParser();
    }

    public double calculate(String exam) {
        return expressionParser.parseExpressions(exam);
    }
}
