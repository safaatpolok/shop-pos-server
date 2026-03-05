package com.shop.payload.dto;

import com.shop.domain.PaymentType;
import com.shop.modal.Branch;
import com.shop.modal.Customer;
import com.shop.modal.OrderItem;
import com.shop.modal.User;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder

public class OrderDTO {

    private  Long id;
    private Double totalAmount;
    private LocalDateTime createdAt;

    private Long branchId;
    private Long customerId;

    private BranchDTO branch;

    private UserDto cashier;


    private Customer customer;
    private PaymentType paymentType;

    private List<OrderItemDTO>items;

}
