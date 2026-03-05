package com.shop.payload.storeAnalytics;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class TimeSeriesDateDTO {

    private List<TimeSeriesDateDTO>points;
    private String period;
}
