package com.shop.service;

import com.shop.payload.branchAnalytics.CategorySalesDTO;
import com.shop.payload.storeAnalytics.*;

import java.util.List;

public interface StoreAnalyticsService {

    StoreOverviewDTO getStoreOverview(Long storeAdminId);
    TimeSeriesDateDTO getSalesTrends(Long storeAdinId, String period);

    List<TimeSeriesPointDTO>getMonthlySalesGraph(Long storeAdminId);
    List<TimeSeriesPointDTO>getDailySalesGraph(Long storeAdminId);

//    List<CategorySalesDTO> getSalesByCategory(Long storeAdminId);

    List<PaymentInsightDTO> getSalesByPaymentMethod(Long storeAdminId);
    List<BranchSalesDTO> getSalesByBranch(Long storeAdminId);
    List<PaymentInsightDTO>getPaymentBreakdown(Long storeAdminId);
    BranchPerformanceDTO getBranchPerformance(Long storeAdminId);

    StoreAlertDTO getStoreAlerts(Long storeAdminId);
}
