package com.deliverysl.luxurydelivery.order.model;

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
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "orders"
)
public class Order{

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(nullable = false)
    private boolean activate;



    //Metodos helpers

    public void calculateTotal(){
        if (!orderlineList.isEmpty()){
            this.total = BigDecimal.ZERO;
            orderlineList.forEach(orderline ->{
                if (orderline.isActivate()){
                    this.total = this.total.add(orderline.getSubtotal());
                }
            });
        }
    }

    public void addOrderline(Orderline orderline){
        this.orderlineList.add(orderline);
        orderline.setOrder(this);
        orderline.setActivate(true);
    }

    public void removeOrderline(Orderline orderline){
        this.orderlineList.stream()
                .filter(ol->ol.getId().equals(orderline.getId()))
                .findFirst()
                .ifPresent(ol->ol.setActivate(false));
    }

}
