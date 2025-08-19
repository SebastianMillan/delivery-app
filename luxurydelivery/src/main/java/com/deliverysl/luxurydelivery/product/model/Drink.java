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
public class Drink extends Product {

    private boolean alcoholic;

    @Enumerated(EnumType.STRING)
    private DrinkSize drinkSize;
}
