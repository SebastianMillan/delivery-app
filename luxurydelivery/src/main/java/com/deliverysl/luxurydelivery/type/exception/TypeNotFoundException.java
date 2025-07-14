package com.deliverysl.luxurydelivery.type.exception;

import jakarta.persistence.EntityNotFoundException;

public class TypeNotFoundException extends EntityNotFoundException {

  public TypeNotFoundException() {super("Types not found");}

  public TypeNotFoundException(Long id){
    super("Type not found id %d".formatted(id));
  }
}
