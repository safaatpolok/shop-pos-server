package com.shop.payload.storeAnalytics;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class StoreAlertDTO {

    private List<String> lowStockAlerts;
    private List<String> noSalesToday;
    private List<String> refundSpikerAlerts;
    private List<String>inactiveCashiers;
}
