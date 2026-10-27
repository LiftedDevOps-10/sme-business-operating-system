package com.lifted.smebos.repository;

import com.lifted.smebos.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByCategory(Expense.ExpenseCategory category);

    List<Expense> findByExpenseDateBetween(
            LocalDateTime startDate,
            LocalDateTime endDate
    );

    List<Expense> findByRecordedById(Long userId);
}