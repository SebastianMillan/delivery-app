package com.deliverysl.luxurydelivery.order.service;

import com.deliverysl.luxurydelivery.order.dto.CreateOrderDTO;
import com.deliverysl.luxurydelivery.order.dto.EditOrderDto;
import com.deliverysl.luxurydelivery.order.exception.OrderNotFoundException;
import com.deliverysl.luxurydelivery.order.mapper.OrderMapper;
import com.deliverysl.luxurydelivery.order.model.Order;
import com.deliverysl.luxurydelivery.order.model.StateOrder;
import com.deliverysl.luxurydelivery.orderline.dto.CreateOrderlineDTO;
import com.deliverysl.luxurydelivery.orderline.exception.OrderlineNotFoundException;
import com.deliverysl.luxurydelivery.orderline.mapper.OrderlineMapper;
import com.deliverysl.luxurydelivery.orderline.model.Orderline;
import com.deliverysl.luxurydelivery.orderline.service.OrderlineService;
import com.deliverysl.luxurydelivery.utils.BaseServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class OrderService extends BaseServiceImpl<Order,Long> {

    private final OrderlineService orderlineService;
    private final OrderMapper orderMapper;
    private final OrderlineMapper orderlineMapper;

    public Order findByIdOrThrow(Long id){
        return findOptionalById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Transactional
    public Order create(CreateOrderDTO createOrderDTO){

        Order order = orderMapper.toEntity(createOrderDTO);
        order.setStateOrder(StateOrder.PENDING); // Estado inicial del pedido
        order.setDateTime(LocalDateTime.now());

        for (CreateOrderlineDTO createOrderlineDTO: createOrderDTO.orderlineDTOList()){
            Orderline orderline = orderlineService.create(createOrderlineDTO);
            order.getOrderlineList().add(orderline);

        }

        order.calculateTotal();
        return save(order);
    }

    @Transactional
    public Order edit(Long idOrder, EditOrderDto editOrderDTO){

        Order order = findByIdOrThrow(idOrder);

        order.calculateTotal();
        order.setStateOrder(StateOrder.valueOf(editOrderDTO.stateOrder()));
        order.setDateTime(LocalDateTime.now());
        //order.setClient();
        //order.setRider();
        //order.setEmployee();
        return save(order);
    }

    @Transactional
    public Orderline addOrderLine(Long idOrder,CreateOrderlineDTO createOrderlineDTO){

        Order order = findByIdOrThrow(idOrder);
        Orderline orderline = orderlineService.create(createOrderlineDTO);
        order.getOrderlineList().add(orderline);

        order.calculateTotal();

        save(order);

        return orderline;

    }

    @Transactional
    public void deleteOrderline(Long idOrder,Long idOrderline){

        Order order = findByIdOrThrow(idOrder);
        Orderline orderline = orderlineService.findByOrIdThrow(idOrderline);

        order.getOrderlineList().remove(orderline);

        orderlineService.delete(orderline);
        order.calculateTotal();

        save(order);

    }

    @Transactional
    public Orderline editOrdeline(Long idOrder,Long idOrderline,CreateOrderlineDTO createOrderlineDTO){

        Order order = findByIdOrThrow(idOrder);
        Orderline editOrderline = order.getOrderlineList()
                .stream().filter(ol->idOrderline.equals(ol.getId()))
                .findFirst()
                .orElseThrow(()->new OrderlineNotFoundException(idOrderline));

        orderlineService.edit(createOrderlineDTO,idOrderline);
        order.calculateTotal();
        save(order);

        return editOrderline;

    }


    public Orderline getOneOrderline(Long idOrder,Long idOrderline){
        Order order = findByIdOrThrow(idOrder);

        return order.getOrderlineList().stream().filter(ol->idOrderline.equals(ol.getId()))
                .findFirst()
                .orElseThrow(()->new OrderlineNotFoundException(idOrderline));

    }






}
