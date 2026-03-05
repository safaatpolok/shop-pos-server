package com.shop.modal;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    private Integer quantity;

    private Double price;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Order order;
}
