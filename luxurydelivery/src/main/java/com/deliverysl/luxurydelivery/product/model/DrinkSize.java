package com.deliverysl.luxurydelivery.product.model;

import lombok.Getter;

@Getter
public enum DrinkSize {
    SMALL(330),
    MEDIUM(500),
    LARGE(1000),
    EXTRA_LARGE(2000);

    private final int milliliters;

    DrinkSize(int milliliters) {
        this.milliliters = milliliters;
    }
}
