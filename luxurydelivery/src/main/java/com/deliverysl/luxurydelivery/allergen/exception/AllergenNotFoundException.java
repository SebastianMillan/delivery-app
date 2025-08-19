package com.deliverysl.luxurydelivery.allergen.exception;

import jakarta.persistence.EntityNotFoundException;

public class AllergenNotFoundException extends EntityNotFoundException {

  public AllergenNotFoundException(String message) {
    super(message);
  }

  public AllergenNotFoundException() {
    super("Allergens not found");
  }

  public AllergenNotFoundException(Long id) {
    super("Allergen not found by id %d".formatted(id));
  }
}
