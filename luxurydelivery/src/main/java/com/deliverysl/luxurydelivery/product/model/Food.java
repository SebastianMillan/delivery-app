package com.deliverysl.luxurydelivery.product.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Food extends Product {

    private int calories;

    // Esta anotación hace que se guarde el enum como un String en BBDD
    @Enumerated(EnumType.STRING)
    private FoodPortion foodPortion;
}
