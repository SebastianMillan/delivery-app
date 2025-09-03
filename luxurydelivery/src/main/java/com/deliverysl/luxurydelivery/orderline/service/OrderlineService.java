package com.deliverysl.luxurydelivery.orderline.service;

import com.deliverysl.luxurydelivery.orderline.dto.CreateOrderlineDTO;
import com.deliverysl.luxurydelivery.orderline.exception.OrderlineNotFoundException;
import com.deliverysl.luxurydelivery.orderline.mapper.OrderlineMapper;
import com.deliverysl.luxurydelivery.orderline.model.Orderline;
import com.deliverysl.luxurydelivery.orderline.repository.OrderlineRepository;
import com.deliverysl.luxurydelivery.product.model.Product;
import com.deliverysl.luxurydelivery.product.service.ProductService;
import com.deliverysl.luxurydelivery.utils.BaseServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderlineService extends BaseServiceImpl<Orderline,Long> {

    private final ProductService productService;
    private final OrderlineMapper orderlineMapper;
    private final OrderlineRepository orderlineRepository;

    //Aunque se accedan a la lineas desde es Order
    //Es util para alguna lectura puntual
    public Orderline findByOrIdThrow(Long id){
        return findOptionalById(id)
                .orElseThrow(() -> new OrderlineNotFoundException(id));
    }

    //No persiste nada desde aquí.Lo hará en el momento en el que se cree un Order con un Orderline
    public Orderline create (CreateOrderlineDTO createOrderlineDTO){

        Product product = productService.findByIdOrThrow(createOrderlineDTO.idProduct());

        Orderline orderline = orderlineMapper.toEntity(createOrderlineDTO,product);
        orderline.calculateSubtotal();
        return orderline;

    }

    //No persiste nada al igual que el metodo de arriba
    public Orderline edit(CreateOrderlineDTO createOrderlineDTO, Long id){

        Product product = productService.findByIdOrThrow(createOrderlineDTO.idProduct());

        Orderline orderline = findByOrIdThrow(id);
        orderline.setQuantity(createOrderlineDTO.quantity());
        orderline.setProduct(product);
        orderline.calculateSubtotal();

        return orderline;

    }

}
