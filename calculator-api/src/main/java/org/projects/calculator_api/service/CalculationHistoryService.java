package org.projects.calculator_api.service;

import org.projects.calculator_api.model.CalculationHistory;
import org.projects.calculator_api.repository.CalculationHistoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class CalculationHistoryService {
    private final CalculationHistoryRepository repository;

    public CalculationHistoryService(CalculationHistoryRepository repository) {
        this.repository = repository;
    }

    // Сохранение вычислений + очистка старых данных
    public void saveCalculation(String expression, double result) {
        CalculationHistory history = new CalculationHistory(expression, result);
        repository.save(history);
        cleanupHistory();  // Очищаем историю при необходимости
    }

    // Получение последних 20 записей
    public List<CalculationHistory> getLast20Calculations() {
        return repository.findTop20ByOrderByCreatedAtDesc();
    }

    // Очистка истории: оставляем только последние 40 записей, если записей больше 50
    @Transactional
    public void cleanupHistory() {
        long count = repository.count();
        if (count > 50) {
            int recordsToDelete = (int) (count - 40);
            repository.deleteOldestRecords(recordsToDelete); // Удаляем только самые старые записи
        }
    }
}

