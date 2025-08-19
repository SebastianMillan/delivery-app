package com.deliverysl.luxurydelivery.product.mapper;

import com.deliverysl.luxurydelivery.allergen.mapper.AllergenMapper;
import com.deliverysl.luxurydelivery.product.dto.ProductDTO;
import com.deliverysl.luxurydelivery.product.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    private final AllergenMapper allergenMapper;

    public ProductDTO toDto(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getImage(),
                product.getPrice(),
                product.getCategory().getName(),
                product.getAllergensList().stream()
                        .map(allergenMapper::toShortDto)
                        .toList()
        );
    }
}
