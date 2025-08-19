package com.deliverysl.luxurydelivery.product.exception;

import jakarta.persistence.EntityNotFoundException;

public class ProductNotFoundException extends EntityNotFoundException {

  public ProductNotFoundException(String message) {
    super(message);
  }

  public ProductNotFoundException() {
    super("Products not found");
  }

  public ProductNotFoundException(Long id) {
    super("Product not found by id %d".formatted(id));
  }
}
