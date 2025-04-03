package org.projects.calculator_api.controller;

import org.projects.calculator_api.util.Calculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.projects.calculator_api.model.CalculationHistory;
import org.projects.calculator_api.service.CalculationHistoryService;

@RestController
@RequestMapping("/api")
public class CalculatorController {

    private final Calculator calculator;
    private final CalculationHistoryService historyService ;
    private final Logger logger = LoggerFactory.getLogger(CalculatorController.class);

    public CalculatorController(Calculator calculator, CalculationHistoryService historyService) {
        this.calculator = calculator;
        this.historyService = historyService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<String> calculate(@RequestParam String expression) {
        if (expression == null || expression.isEmpty()) {
            throw new IllegalArgumentException("Выражение не может быть пустым");
        }
        try {
            double result = calculator.calculate(expression);
            historyService.saveCalculation(expression,result);
            logger.info("Вычислено: " + expression + " = " + result);
            return ResponseEntity.ok("Результат: " + result);
        } catch (IllegalArgumentException | ArithmeticException e) {
            logger.error("Ошибка при вычислении выражения {}: {}", expression, e.getMessage());
            return ResponseEntity.status(400).body("Ошибка: " + e.getMessage());
        }
    }

    @GetMapping("/history")
    public ResponseEntity<List<CalculationHistory>> getHistory() {
        return ResponseEntity.ok(historyService.getLast20Calculations());
    }
}
