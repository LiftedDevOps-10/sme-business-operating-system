package com.lifted.smebos.controller;

import com.lifted.smebos.entity.Sale;
import com.lifted.smebos.repository.SaleRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/reports")
public class ReportsController {

    private final SaleRepository saleRepository;

    public ReportsController(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @GetMapping
    public Map<String, String> getReportsDashboard() {
        return Map.of(
                "message", "Welcome to the SME-BOS Reports Dashboard",
                "access", "OWNER_AND_MANAGER_ONLY"
        );
    }

    @GetMapping("/sales")
    public Map<String, Object> getSalesReport(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate
    ) {

        // If neither date is supplied, return the full sales report.
        if (startDate == null && endDate == null) {
            return buildFullSalesReport();
        }

        // Require both dates when date filtering is requested.
        if (startDate == null || endDate == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Provide both startDate and endDate in YYYY-MM-DD format."
            );
        }

        // Reject a date range whose start is after its end.
        if (startDate.isAfter(endDate)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "startDate must be on or before endDate."
            );
        }

        LocalDateTime startDateTime = startDate.atStartOfDay();
        LocalDateTime endDateTime = endDate.plusDays(1).atStartOfDay();

        BigDecimal totalSalesValue =
                saleRepository.calculateTotalSalesValueBetween(
                        startDateTime, endDateTime
                );

        BigDecimal paidSalesRevenue =
                saleRepository.calculatePaidSalesRevenueBetween(
                        startDateTime, endDateTime
                );

        BigDecimal outstandingSalesValue =
                saleRepository.calculateOutstandingSalesValueBetween(
                        startDateTime, endDateTime
                );

        BigDecimal cancelledSalesValue =
                saleRepository.calculateCancelledSalesValueBetween(
                        startDateTime, endDateTime
                );

        long totalTransactions =
                saleRepository.countBySaleDateGreaterThanEqualAndSaleDateLessThan(
                        startDateTime, endDateTime
                );

        long paidTransactions =
                countTransactions(
                        Sale.PaymentStatus.PAID,
                        startDateTime,
                        endDateTime
                );

        long pendingTransactions =
                countTransactions(
                        Sale.PaymentStatus.PENDING,
                        startDateTime,
                        endDateTime
                );

        long partialTransactions =
                countTransactions(
                        Sale.PaymentStatus.PARTIAL,
                        startDateTime,
                        endDateTime
                );

        long cancelledTransactions =
                countTransactions(
                        Sale.PaymentStatus.CANCELLED,
                        startDateTime,
                        endDateTime
                );

        return Map.ofEntries(
                Map.entry("report", "Sales Summary"),
                Map.entry("periodStart", startDate.toString()),
                Map.entry("periodEnd", endDate.toString()),
                Map.entry("totalSalesValue", totalSalesValue),
                Map.entry("paidSalesRevenue", paidSalesRevenue),
                Map.entry("outstandingSalesValue", outstandingSalesValue),
                Map.entry("cancelledSalesValue", cancelledSalesValue),
                Map.entry("totalTransactions", totalTransactions),
                Map.entry("paidTransactions", paidTransactions),
                Map.entry("pendingTransactions", pendingTransactions),
                Map.entry("partialTransactions", partialTransactions),
                Map.entry("cancelledTransactions", cancelledTransactions)
        );
    }

    private long countTransactions(
            Sale.PaymentStatus status,
            LocalDateTime startDateTime,
            LocalDateTime endDateTime
    ) {
        return saleRepository
                .countByPaymentStatusAndSaleDateGreaterThanEqualAndSaleDateLessThan(
                        status,
                        startDateTime,
                        endDateTime
                );
    }

    private Map<String, Object> buildFullSalesReport() {

        BigDecimal totalSalesValue =
                saleRepository.calculateTotalSalesValue();

        BigDecimal paidSalesRevenue =
                saleRepository.calculatePaidSalesRevenue();

        BigDecimal outstandingSalesValue =
                saleRepository.calculateOutstandingSalesValue();

        BigDecimal cancelledSalesValue =
                saleRepository.calculateCancelledSalesValue();

        long totalTransactions = saleRepository.count();

        long paidTransactions =
                saleRepository.countByPaymentStatus(
                        Sale.PaymentStatus.PAID
                );

        long pendingTransactions =
                saleRepository.countByPaymentStatus(
                        Sale.PaymentStatus.PENDING
                );

        long partialTransactions =
                saleRepository.countByPaymentStatus(
                        Sale.PaymentStatus.PARTIAL
                );

        long cancelledTransactions =
                saleRepository.countByPaymentStatus(
                        Sale.PaymentStatus.CANCELLED
                );

        return Map.ofEntries(
                Map.entry("report", "Sales Summary"),
                Map.entry("totalSalesValue", totalSalesValue),
                Map.entry("paidSalesRevenue", paidSalesRevenue),
                Map.entry("outstandingSalesValue", outstandingSalesValue),
                Map.entry("cancelledSalesValue", cancelledSalesValue),
                Map.entry("totalTransactions", totalTransactions),
                Map.entry("paidTransactions", paidTransactions),
                Map.entry("pendingTransactions", pendingTransactions),
                Map.entry("partialTransactions", partialTransactions),
                Map.entry("cancelledTransactions", cancelledTransactions)
        );
    }
}

