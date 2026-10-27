package com.lifted.smebos.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@EntityListeners(org.springframework.data.jpa.domain.support.AuditingEntityListener.class)
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private ExpenseCategory category;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal amount;

    @Column(name = "expense_date", nullable = false)
    private LocalDateTime expenseDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recorded_by", nullable = false)
    private User recordedBy;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public enum ExpenseCategory {
        RENT,
        UTILITIES,
        SALARIES,
        TRANSPORT,
        MARKETING,
        SUPPLIES,
        MAINTENANCE,
        OTHER
    }

    public Expense() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDateTime expenseDate) {
        this.expenseDate = expenseDate;
    }

    public User getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(User recordedBy) {
        this.recordedBy = recordedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}