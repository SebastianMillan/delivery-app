package com.deliverysl.luxurydelivery.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class OrderLine {

    @Id @GeneratedValue
    private Long id;

    private int quantity;
    private double subtotal;

    //Unidireccional
    @ManyToOne
    @JoinColumn(
            name = "product_id"
    )
    private Product product;



}
