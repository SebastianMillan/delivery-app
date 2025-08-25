package com.deliverysl.luxurydelivery.order.model;

import com.deliverysl.luxurydelivery.model.Client;
import com.deliverysl.luxurydelivery.model.Employee;
import com.deliverysl.luxurydelivery.model.Rider;
import com.deliverysl.luxurydelivery.orderline.model.Orderline;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.core.util.Json;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.BeanInfo;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "orders"
)
public class Order {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    private StateOrder stateOrder;

    private LocalDateTime dateTime;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "order_id",nullable = false)
    private List<Orderline> orderlineList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rider_id")
    private Rider rider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    //Metodos helpers

    public void calculateTotal(){
        if (!orderlineList.isEmpty()){
            this.total = BigDecimal.ZERO;
            orderlineList.forEach(orderline -> this.total = this.total.add(orderline.getSubtotal()));
        }
    }


}
