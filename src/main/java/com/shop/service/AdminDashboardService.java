package com.shop.service;

import com.shop.payload.adminAnalytics.DashboardSummaryDTO;
import com.shop.payload.adminAnalytics.StoreRegistrationStateDTO;
import com.shop.payload.adminAnalytics.StoreStatusDistributionDTO;

import java.util.List;

public interface AdminDashboardService {

    DashboardSummaryDTO getDashboardSummary();
    List<StoreRegistrationStateDTO> getLast7DayRegistrationState();
    StoreStatusDistributionDTO getStoreStatusDistribution();
}
