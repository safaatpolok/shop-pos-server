package com.shop.payload.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.domain.PaymentType;
import com.shop.modal.Branch;
import com.shop.modal.Order;
import com.shop.modal.ShiftReport;
import com.shop.modal.User;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RefundDTO {

    private Long id;



    private OrderDTO order;
    private Long orderId;

    private String reason;

    private Double amount;

//    private ShiftReport shiftReport;
    private Long shiftReportId;

    private UserDto cashier;
    private String cashierName;
    private Long branchId;

    private BranchDTO branch;


    private PaymentType paymentType;
    private LocalDateTime createdAt;


}
