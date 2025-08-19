package com.deliverysl.luxurydelivery.model;

import com.deliverysl.luxurydelivery.product.model.Product;
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

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
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
