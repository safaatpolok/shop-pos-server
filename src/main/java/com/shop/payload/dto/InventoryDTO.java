package com.shop.payload.dto;

import com.shop.modal.Branch;
import com.shop.modal.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Builder

public class InventoryDTO {


    private Long id;

    private Branch branch;

    private Long branchId;
    private Long productId;

    private ProductDTO product;

    private Integer quantity;
    private LocalDateTime lastUpdate;

}
