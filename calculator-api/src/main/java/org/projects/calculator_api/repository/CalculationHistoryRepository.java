package org.projects.calculator_api.repository;

import org.projects.calculator_api.model.CalculationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CalculationHistoryRepository extends JpaRepository<CalculationHistory, Long> {

    // Получение последних 20 записей
    List<CalculationHistory> findTop20ByOrderByCreatedAtDesc();


    // Получение самых старых записей (для удаления)
    @Query("SELECT c FROM CalculationHistory c ORDER BY c.createdAt ASC")
    List<CalculationHistory> findOldestRecords(Pageable pageable);

    // Удаление самых старых записей
    @Modifying
    @Query("DELETE FROM CalculationHistory c WHERE c.id IN " +
            "(SELECT c2.id FROM CalculationHistory c2 ORDER BY c2.createdAt ASC LIMIT :limit)")
    void deleteOldestRecords(int limit);
}
