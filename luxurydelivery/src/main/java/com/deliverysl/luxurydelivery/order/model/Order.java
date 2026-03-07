package com.deliverysl.luxurydelivery.order.model;

import com.deliverysl.luxurydelivery.order.statemachine.state.StateOrder;
import com.deliverysl.luxurydelivery.utils.ActivableEntity;
import com.deliverysl.luxurydelivery.user.model.Client;
import com.deliverysl.luxurydelivery.user.model.Employee;
import com.deliverysl.luxurydelivery.user.model.Rider;
import com.deliverysl.luxurydelivery.orderline.model.Orderline;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "orders"
)
public class Order extends ActivableEntity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private StateOrder stateOrder;

    private LocalDateTime createDate;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Orderline> orderlineList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rider_id")
    private Rider rider;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "client_id",nullable = false)
    //Tanto con optional como nullable en false,hacemos que siempre tenga el pedido un cliente y permitiendo
    // que tanto rider como employee tengan la posibilidad de ser nulo depediendo del estado del pedido (ya viene por defecto asi, no hace falta ponerlo como true)
    private Client client;

    //Metodos helpers

    public void calculateTotal(){
        this.total = BigDecimal.ZERO;
        if (orderlineList.isEmpty() || orderlineList==null){
            return;
        }
        for (Orderline orderline : orderlineList){
            if (orderline.isActive()){
                orderline.calculateSubtotal();
                BigDecimal subtotal = orderline.getSubtotal();
                if (subtotal!=null){
                    this.total = this.total.add(subtotal);
                }
            }
        }
    }

    public void addOrderline(Orderline orderline){
        this.orderlineList.add(orderline);
        orderline.setOrder(this);
        orderline.setActive(true);
    }

    public void removeOrderline(Orderline orderline){
        this.orderlineList.remove(orderline);
        orderline.setActive(false);
        orderline.setOrder(null);
    }

}
