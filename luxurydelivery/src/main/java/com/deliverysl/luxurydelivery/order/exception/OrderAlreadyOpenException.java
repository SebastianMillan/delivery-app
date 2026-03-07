package com.deliverysl.luxurydelivery.order.exception;

public class OrderAlreadyOpenException extends RuntimeException {
  public OrderAlreadyOpenException(String message) {super(message);}
  public OrderAlreadyOpenException(Long clienteId){
      super("Client with ID %d".formatted(clienteId)+" already has an open order");
  }
}
