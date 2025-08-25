package com.deliverysl.luxurydelivery.orderline.model;

import com.deliverysl.luxurydelivery.product.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Orderline {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private int quantity;
    private BigDecimal subtotal;

    //Unidireccional
    @ManyToOne
    @JoinColumn(
            name = "product_id"
    )
    private Product product;

    //Metodo Helper
    public void calculateSubtotal(){
        if (product!=null){
            this.subtotal = product.getPrice().multiply(BigDecimal.valueOf(quantity));
        }
    }



}
