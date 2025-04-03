package org.projects.calculator_api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CalculationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String expression;
    private double result;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Конструкторы
    public CalculationHistory() {
    }

    public CalculationHistory(String expression, double result) {
        this.expression = expression;
        this.result = result;
        this.createdAt = LocalDateTime.now();
    }

    // Геттеры
    public Long getId() {
        return id;
    }

    public String getExpression() {
        return expression;
    }

    public double getResult() {
        return result;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Сеттеры (ID и createdAt менять не будем)
    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setResult(double result) {
        this.result = result;
    }
}
