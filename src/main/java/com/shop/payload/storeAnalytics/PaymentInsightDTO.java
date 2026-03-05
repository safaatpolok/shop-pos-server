package com.shop.payload.storeAnalytics;


import com.shop.domain.PaymentType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class PaymentInsightDTO {
    private PaymentType paymentMethod;
    private Double totalAmount;
}
