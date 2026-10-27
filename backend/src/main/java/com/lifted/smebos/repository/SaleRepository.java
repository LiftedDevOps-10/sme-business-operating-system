package com.lifted.smebos.repository;

import com.lifted.smebos.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findByCustomerId(Long customerId);

    List<Sale> findByCreatedById(Long userId);

    List<Sale> findBySaleDateBetween(
            LocalDateTime startDate,
            LocalDateTime endDate
    );

    long countByPaymentStatus(Sale.PaymentStatus paymentStatus);

    @Query("SELECT COALESCE(SUM(s.totalAmount), 0) FROM Sale s")
    BigDecimal calculateTotalSalesValue();

    @Query("""
            SELECT COALESCE(SUM(s.totalAmount), 0)
            FROM Sale s
            WHERE s.paymentStatus = :#{T(com.lifted.smebos.entity.Sale.PaymentStatus).PAID}
            """)
    BigDecimal calculatePaidSalesRevenue();

    @Query("""
            SELECT COALESCE(SUM(s.totalAmount), 0)
            FROM Sale s
            WHERE s.paymentStatus IN (
                com.lifted.smebos.entity.Sale.PaymentStatus.PENDING,
                com.lifted.smebos.entity.Sale.PaymentStatus.PARTIAL
            )
            """)
    BigDecimal calculateOutstandingSalesValue();

    @Query("""
            SELECT COALESCE(SUM(s.totalAmount), 0)
            FROM Sale s
            WHERE s.paymentStatus =
                com.lifted.smebos.entity.Sale.PaymentStatus.CANCELLED
            """)
    BigDecimal calculateCancelledSalesValue();

    @Query("""
            SELECT COALESCE(SUM(s.totalAmount), 0)
            FROM Sale s
            WHERE s.saleDate >= :startDate
              AND s.saleDate < :endDate
            """)
    BigDecimal calculateTotalSalesValueBetween(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("""
            SELECT COALESCE(SUM(s.totalAmount), 0)
            FROM Sale s
            WHERE s.paymentStatus =
                com.lifted.smebos.entity.Sale.PaymentStatus.PAID
              AND s.saleDate >= :startDate
              AND s.saleDate < :endDate
            """)
    BigDecimal calculatePaidSalesRevenueBetween(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("""
            SELECT COALESCE(SUM(s.totalAmount), 0)
            FROM Sale s
            WHERE s.paymentStatus IN (
                com.lifted.smebos.entity.Sale.PaymentStatus.PENDING,
                com.lifted.smebos.entity.Sale.PaymentStatus.PARTIAL
            )
              AND s.saleDate >= :startDate
              AND s.saleDate < :endDate
            """)
    BigDecimal calculateOutstandingSalesValueBetween(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("""
            SELECT COALESCE(SUM(s.totalAmount), 0)
            FROM Sale s
            WHERE s.paymentStatus =
                com.lifted.smebos.entity.Sale.PaymentStatus.CANCELLED
              AND s.saleDate >= :startDate
              AND s.saleDate < :endDate
            """)
    BigDecimal calculateCancelledSalesValueBetween(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    long countBySaleDateGreaterThanEqualAndSaleDateLessThan(
            LocalDateTime startDate,
            LocalDateTime endDate
    );

    long countByPaymentStatusAndSaleDateGreaterThanEqualAndSaleDateLessThan(
            Sale.PaymentStatus paymentStatus,
            LocalDateTime startDate,
            LocalDateTime endDate
    );
}

