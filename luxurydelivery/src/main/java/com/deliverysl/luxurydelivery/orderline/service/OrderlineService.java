package com.deliverysl.luxurydelivery.orderline.service;

import com.deliverysl.luxurydelivery.orderline.dto.CreateOrderlineDTO;
import com.deliverysl.luxurydelivery.orderline.dto.EditOrderlineDTO;
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
    //Modificamos de la linea de pedido solamente la cantidad
    //Haciendolo mas parecido a un carrito
    public Orderline edit(EditOrderlineDTO editOrderlineDTO, Long id){

        //Buscamos la linea de pedido
        Orderline orderline = findByOrIdThrow(id);
        //Modificamos la cantidad del pedido
        orderline.setQuantity(editOrderlineDTO.quantity());
        //orderline.setProduct(product);
        orderline.calculateSubtotal();

        return orderline;

    }
}
