package com.deliverysl.luxurydelivery.order.service;

import com.deliverysl.luxurydelivery.utils.ActivableEntity;
import com.deliverysl.luxurydelivery.order.dto.CreateOrderDTO;
import com.deliverysl.luxurydelivery.order.dto.EditOrderDto;
import com.deliverysl.luxurydelivery.order.exception.OrderNotFoundException;
import com.deliverysl.luxurydelivery.order.mapper.OrderMapper;
import com.deliverysl.luxurydelivery.order.model.Order;
import com.deliverysl.luxurydelivery.order.model.StateOrder;
import com.deliverysl.luxurydelivery.order.repository.OrderRepository;
import com.deliverysl.luxurydelivery.orderline.dto.CreateOrderlineDTO;
import com.deliverysl.luxurydelivery.orderline.exception.OrderlineNotFoundException;
import com.deliverysl.luxurydelivery.orderline.model.Orderline;
import com.deliverysl.luxurydelivery.orderline.repository.OrderlineRepository;
import com.deliverysl.luxurydelivery.orderline.service.OrderlineService;
import com.deliverysl.luxurydelivery.user.exception.UserNotFoundException;
import com.deliverysl.luxurydelivery.user.model.Client;
import com.deliverysl.luxurydelivery.user.model.Employee;
import com.deliverysl.luxurydelivery.user.model.Rider;
import com.deliverysl.luxurydelivery.user.service.ClientService;
import com.deliverysl.luxurydelivery.user.service.EmployeeService;
import com.deliverysl.luxurydelivery.user.service.RiderService;
import com.deliverysl.luxurydelivery.utils.BaseServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService extends BaseServiceImpl<Order,Long> {

    private final OrderlineService orderlineService;
    private final EmployeeService employeeService;
    private final ClientService clientService;
    private final RiderService riderService;
    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final OrderlineRepository orderlineRepository;

    public Order findByIdOrThrow(Long id){
        return findOptionalById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Transactional
    public Order create(CreateOrderDTO createOrderDTO){

        Employee employee = employeeService.findOptionalById(createOrderDTO.idEmployee())
                .orElseThrow(() -> new UserNotFoundException(createOrderDTO.idEmployee()));

        Client client = clientService.findOptionalById(createOrderDTO.idClient())
                .orElseThrow(() -> new UserNotFoundException(createOrderDTO.idEmployee()));

        Rider rider = riderService.findOptionalById(createOrderDTO.idRider())
                .orElseThrow(() -> new UserNotFoundException(createOrderDTO.idRider()));


        Order order = orderMapper.toEntity(createOrderDTO,employee,client,rider);
        order.setStateOrder(StateOrder.PENDING); // Estado inicial del pedido
        order.setCreateDate(LocalDateTime.now());

        for (CreateOrderlineDTO createOrderlineDTO: createOrderDTO.orderlineDTOList()){
            Orderline orderline = orderlineService.create(createOrderlineDTO);
            order.addOrderline(orderline);
        }

        order.calculateTotal();
        return save(order);
    }

    @Transactional
    public Order edit(Long idOrder, EditOrderDto editOrderDTO){

        Order order = findByIdOrThrow(idOrder);
        Employee employee = employeeService.findOptionalById(editOrderDTO.idEmployee())
                .orElseThrow(() -> new UserNotFoundException(editOrderDTO.idEmployee()));
        Client client = clientService.findOptionalById(editOrderDTO.idClient())
                .orElseThrow(() -> new UserNotFoundException(editOrderDTO.idClient()));
        Rider rider = riderService.findOptionalById(editOrderDTO.idRider())
                .orElseThrow(() -> new UserNotFoundException(editOrderDTO.idRider()));

        order.calculateTotal();
        order.setStateOrder(StateOrder.valueOf(editOrderDTO.stateOrder()));
        //order.setCreateDate(LocalDateTime.now());
        order.setClient(client);
        order.setRider(rider);
        order.setEmployee(employee);
        return save(order);
    }

    @Transactional
    public Orderline addOrderLine(Long idOrder,CreateOrderlineDTO createOrderlineDTO){

        Order order = findByIdOrThrow(idOrder);
        Orderline orderline = orderlineService.create(createOrderlineDTO);
        order.addOrderline(orderline);
        orderlineService.save(orderline);
        order.calculateTotal();
        save(order);

        return orderline;

    }

    @Transactional
    public void deactiveOrderLineById(Long idOrder, Long idOrderline){

        Order order = findByIdOrThrow(idOrder);
        Orderline orderline = orderlineService.findByOrIdThrow(idOrderline);
        order.removeOrderline(orderline);
        order.calculateTotal();
        deactivate(idOrderline);
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

    /*@Transactional
    public Orderline toggleOrderline(Long idOrder,Long idOrderline){

        Order order = findByIdOrThrow(idOrder);

        Orderline orderline = order.getOrderlineList()
                .stream()
                .filter(ol -> idOrderline.equals(ol.getId()))
                .findFirst()
                .orElseThrow(() -> new OrderlineNotFoundException(idOrderline));

        deactivate(orderline.getId());

        save(order);

        return  orderline;
    }*/

    public List<Orderline> findByAllOrderlinesActiveTrue(Long idOrder){
        Order order = findByIdOrThrow(idOrder);

        return order.getOrderlineList().stream().filter(ActivableEntity::isActive).toList();
    }
}
