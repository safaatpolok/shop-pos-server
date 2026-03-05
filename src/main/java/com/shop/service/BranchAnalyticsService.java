package com.shop.service;

import com.shop.modal.PaymentSummary;
import com.shop.payload.branchAnalytics.*;

import java.time.LocalDate;
import java.util.List;

public interface BranchAnalyticsService {

    List<DailySalesDTO> getDailySalesChart(Long branchId, int days);
    List<ProductPerformanceDTO>getTopProductsByQuantityWithPercentage(Long branchId);
    List<CashierPerformanceDTO> getTopCashierPerformanceByOrders(Long branchId);
    List<CategorySalesDTO> getCategoryWiseSalesBreakdown(Long branchId, LocalDate date);
    BranchDashboardOverviewDTO getBranchOverview(Long branchId);

    List<PaymentSummary> getPaymentMethodBreakdown(Long branchId,LocalDate date);
}
