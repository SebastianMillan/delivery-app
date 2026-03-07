package com.deliverysl.luxurydelivery.order.service;

import com.deliverysl.luxurydelivery.order.exception.*;
import com.deliverysl.luxurydelivery.order.statemachine.event.EventOrder;
import com.deliverysl.luxurydelivery.orderline.dto.EditOrderlineDTO;
import com.deliverysl.luxurydelivery.orderline.mapper.OrderlineMapper;
import com.deliverysl.luxurydelivery.orderline.repository.OrderlineRepository;
import com.deliverysl.luxurydelivery.product.service.ProductService;
import com.deliverysl.luxurydelivery.user.model.Employee;
import com.deliverysl.luxurydelivery.user.model.Rider;
import com.deliverysl.luxurydelivery.utils.ActivableEntity;
import com.deliverysl.luxurydelivery.order.dto.CreateOrderDTO;
import com.deliverysl.luxurydelivery.order.mapper.OrderMapper;
import com.deliverysl.luxurydelivery.order.model.Order;
import com.deliverysl.luxurydelivery.order.statemachine.state.StateOrder;
import com.deliverysl.luxurydelivery.order.repository.OrderRepository;
import com.deliverysl.luxurydelivery.orderline.dto.CreateOrderlineDTO;
import com.deliverysl.luxurydelivery.orderline.model.Orderline;
import com.deliverysl.luxurydelivery.orderline.service.OrderlineService;
import com.deliverysl.luxurydelivery.user.exception.UserNotFoundException;
import com.deliverysl.luxurydelivery.user.model.Client;
import com.deliverysl.luxurydelivery.user.service.ClientService;
import com.deliverysl.luxurydelivery.user.service.EmployeeService;
import com.deliverysl.luxurydelivery.user.service.RiderService;
import com.deliverysl.luxurydelivery.utils.BaseServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService extends BaseServiceImpl<Order,Long> {

    private final OrderlineService orderlineService;
    private final EmployeeService employeeService;
    private final ClientService clientService;
    private final RiderService riderService;
    private final OrderMapper orderMapper;
    private final OrderlineMapper orderlineMapper;
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final OrderlineRepository orderlineRepository;
    private final StateMachineFactory<StateOrder,EventOrder> stateMachineFactory;


    public Order findByIdOrThrow(Long id){
        return findOptionalById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    //Abre el pedido
    @Transactional
    public Order create(CreateOrderDTO createOrderDTO){

        //Busco al cliente para que salte la excepción en caso de no existir
        //Entiendo que quizas no sea necesario ya que el DTO lleva el id del cliente
        //Pero si no, saltaría el codigo 500 sin especificar el error, te dejo a ti la decisión jefe
        Client client = clientService.findOptionalById(createOrderDTO.idClient())
                .orElseThrow(() -> new UserNotFoundException(createOrderDTO.idClient()));

        //Comprobamos si el cliente tiene algun pedido actualmente abierto y activo
        //Si tiene uno abierto, no podria crear otro pedido abierto
        //Salta una excepción
        if (orderRepository.existsByStateOrderAndClient_IdAndActiveTrue(StateOrder.OPENED, client.getId())){
            throw new OrderAlreadyOpenException(client.getId());
        }

        Order order = orderMapper.toEntity(createOrderDTO, client);
        order.setStateOrder(StateOrder.OPENED); // Estado inicial del pedido
        order.setCreateDate(LocalDateTime.now());
        order.calculateTotal();
        return save(order);

    }

    //Añade una linea de pedido cuando el estado sea OPENED
    @Transactional
    public Orderline addOrderLine(Long idOrder,CreateOrderlineDTO createOrderlineDTO){

        Order order = findByIdOrThrow(idOrder);

        //Si el pedido no esta abierto no dejará poder añadir lineas de pedido
        if(order.getStateOrder()!=StateOrder.OPENED || !order.isActive()){
            throw new OrderNotOpenedException(order.getId());
        }
        //Traemos la linea de pedido de la base de datos
        Optional<Orderline> existOrdeline = orderlineRepository
                .findByOrderIdAndProductId(idOrder,createOrderlineDTO.idProduct());

        Orderline orderline;

        //Si existe un producto en una linea de pedido
        if (existOrdeline.isPresent()){
            orderline = existOrdeline.get();
            //Sumamos la cantidad que teniamos ya mas la nueva cantidad añadida
            orderline.setQuantity(orderline.getQuantity() + createOrderlineDTO.quantity());
            orderline.calculateSubtotal();
        }
        else{
            orderline = orderlineService.create(createOrderlineDTO);
            order.addOrderline(orderline);
        }

        order.calculateTotal();
        return orderlineRepository.save(orderline);
    }

    //Elimina una linea de pedido o lineas en un pedido con estado OPENED
    @Transactional
    public void removeOrderline(Long idOrder, Long idOrderline){

        Order order = findByIdOrThrow(idOrder);

        if(order.getStateOrder()!=StateOrder.OPENED || !order.isActive()){
            throw new OrderNotOpenedException(order.getId());
        }

        Orderline orderline = orderlineService.findByOrIdThrow(idOrderline);
        order.removeOrderline(orderline);
        order.calculateTotal();
        save(order);

    }

    //Solo se podra editar el pedido en estado abierto
    @Transactional
    public Orderline editOrdeline(Long idOrder, Long idOrderline, EditOrderlineDTO editOrderlineDTO){

        Order order = findByIdOrThrow(idOrder);

        if(order.getStateOrder()!=StateOrder.OPENED || !order.isActive()){
            throw new OrderNotOpenedException(order.getId());
        }

        Orderline editOrderline = orderlineService.findByOrIdThrow(idOrderline);

        orderlineService.edit(editOrderlineDTO,idOrderline);
        order.calculateTotal();
        save(order);

        return editOrderline;

    }

    public Orderline getOneOrderline(Long idOrder,Long idOrderline){
        //Solamente uso la busqueda para que en caso de no encontrar un pedido salte la excepcion
        findByIdOrThrow(idOrder);
        return orderlineService.findByOrIdThrow(idOrderline);
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

    private Order changeState(Order order, EventOrder event){

        //Construimos la máquina de estados
        //Se usa un string para identificar la máquina, no por un Long
        StateMachine<StateOrder,EventOrder> stateMachine = stateMachineFactory.getStateMachine(order.getId().toString());

        // Proceso de sincronización de la máquina de estados
        // Parar la máquina por si estuviera arrancada
        stateMachine.stop();
        // Reseteo de estado interno
        // La máquina no sabe en que estado se encuentra el pedido(La máquina solo se encarga del cambio de estado)
        // Al arrancar la máquina, esta por defecto empezará en el primer estado (Opened)
        // Le indicamos que el estado en el que se debe encontrar la máquina es el actual del pedido(Según el estado en BD)
        System.out.println("Estado en BD" + order.getStateOrder());
        stateMachine.getStateMachineAccessor().doWithAllRegions(access -> access.resetStateMachine(
                        new DefaultStateMachineContext<>(order.getStateOrder(), null, null, null)
        )

        );
        //Una vez sincronizada, se arranca la máquina de estado una vez establecido su estado
        stateMachine.start();
        System.out.println("Machine state: " + stateMachine.getState().getId());

        //Enviamos el evento para disparar la transicion
        boolean accepted = stateMachine.sendEvent(event);

        if (!accepted) {
            throw new OrderInvalidStateTransitionException(order.getId(), order.getStateOrder(), event);
        }

        //Cambio de la nueva transición
        order.setStateOrder(stateMachine.getState().getId());
        return order;
    }

    @Transactional
    public Order clientConfirmOrder(Long idOrder){

        Order order = findByIdOrThrow(idOrder);

        //Comprobamos que el pedido se encuentre en el estado Opened o activo
        if (order.getStateOrder()!=StateOrder.OPENED || !order.isActive()){
            throw new OrderNotOpenedException(idOrder);
        }

        //Comprobamos si el pedido tiene líneas de pedido(que esten activas)
        if (!orderlineRepository.existsByOrder_IdAndActiveTrue(idOrder)){
            throw new OrderWithoutOrderlinesException(idOrder);
        }

        changeState(order,EventOrder.START_PENDING);
        return save(order);
    }

    @Transactional
    public Order clientCancelOrder(Long idOrder){

        Order order = findByIdOrThrow(idOrder);

        //Para que el cliente pueda cancelar este debe estar solo en estado Opened y activo
        if(order.getStateOrder()!=StateOrder.OPENED || !order.isActive()){
            throw new OrderNotOpenedException(idOrder);
        }

        changeState(order, EventOrder.CANCEL);
        return save(order);
    }

    @Transactional
    public Order clientReorder(Long idOrder){

        Order order = findByIdOrThrow(idOrder);

        if(order.getStateOrder()!=StateOrder.CANCELLED && order.getStateOrder()!= StateOrder.DELIVERED){
            throw new OrderCannotBeReorder(idOrder) ;
        }

        if (orderRepository.existsByStateOrderAndClient_IdAndActiveTrue(StateOrder.OPENED, order.getClient().getId())){
            throw new OrderAlreadyOpenException(order.getClient().getId());
        }

        Order newOrder = new Order();

        newOrder.setClient(order.getClient());
        newOrder.setActive(true);
        newOrder.setStateOrder(StateOrder.OPENED);
        newOrder.setCreateDate(LocalDateTime.now());
        newOrder.setOrderlineList(new ArrayList<>());
        for (Orderline ordeline : order.getOrderlineList()) {
            Orderline newLine = new Orderline();
            newLine.setProduct(ordeline.getProduct());
            newLine.setQuantity(ordeline.getQuantity());
            newLine.setActive(true);
            newLine.calculateSubtotal();

            newOrder.addOrderline(newLine);
        }

        newOrder.calculateTotal();

        return save(newOrder);
    }

    @Transactional
    public Order bossConfirmOrder(Long idOrder,Long idEmployee){

        Order order = findByIdOrThrow(idOrder);

        //Comprobamos que el pedido se encuentre en el estado Pending
        if (order.getStateOrder()!=StateOrder.PENDING){
            throw new OrderNotPendingException(idOrder);
        }

        Employee employee = employeeService.findOptionalById(idEmployee).orElseThrow(()
                -> new UserNotFoundException(idEmployee));


        order.setEmployee(employee);

        changeState(order,EventOrder.CONFIRM);
        return save(order);
    }

    @Transactional
    public Order employeeStartReady(Long idOrder){

        Order order = findByIdOrThrow(idOrder);

        //Comprobamos que el pedido se encuentre en el estado Pending
        if (order.getStateOrder()!=StateOrder.IN_KITCHEN){
            throw new OrderNotPendingException(idOrder);
        }

        changeState(order,EventOrder.START_READY);
        return save(order);
    }

    @Transactional
    public Order riderStartDelivery(Long idOrder,Long idRider){

        Order order = findByIdOrThrow(idOrder);

        if (order.getStateOrder()!=StateOrder.READY){
            throw new OrderNotPendingException(idOrder);
        }

        Rider rider = riderService.findOptionalById(idRider)
                .orElseThrow(() -> new UserNotFoundException(idRider));

        if(order.getRider()!=null){
            throw new RuntimeException("Tiene un rider asignado");
        }

        if(orderRepository.existsByRider_idAndStateOrder(idRider,StateOrder.ON_DELIVERY)){
            throw new RuntimeException("Ya tiene un pedido en reparto");
        }

        order.setRider(rider);
        changeState(order,EventOrder.START_DELIVERY);
        return save(order);

    }

    @Transactional
    public Order riderDelivered(Long idOrder,Long idRider){

        Order order = findByIdOrThrow(idOrder);

        if (order.getStateOrder()!=StateOrder.ON_DELIVERY){
            throw new OrderNotPendingException(idOrder);
        }

        Rider rider = riderService.findOptionalById(idRider)
                .orElseThrow(() -> new UserNotFoundException(idRider));

       if (order.getRider().getId()!=rider.getId()){
           throw new RuntimeException("No pertenece el rider al pedido");
       }

        changeState(order,EventOrder.DELIVER);
        return save(order);

    }

}
