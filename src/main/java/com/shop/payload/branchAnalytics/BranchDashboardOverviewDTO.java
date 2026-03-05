package com.shop.payload.branchAnalytics;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BranchDashboardOverviewDTO {

    private BigDecimal totalSales;
    private Long quantitySold;

    private double salesGrowth;



    private int ordersToday;
    private double orderGrowth;
    private int activeCashiers;
    private double cashierGrowth;

    private int lowStockItems;
    private double lowStockGrowth;
}
