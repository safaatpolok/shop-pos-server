package com.shop.payload.adminAnalytics;


import ch.qos.logback.classic.spi.LoggingEventVO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class  StoreStatusDistributionDTO {
    private Long active;
    private Long blocked;
    private Long pending;


}
