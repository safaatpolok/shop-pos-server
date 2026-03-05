package com.shop.modal;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String name;

    @ManyToOne

    private Store store;

}
