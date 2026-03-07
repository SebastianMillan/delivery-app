package com.deliverysl.luxurydelivery.category.service;

import com.deliverysl.luxurydelivery.category.dto.CategoryCreateDTO;
import com.deliverysl.luxurydelivery.category.exception.CategoryNotFoundException;
import com.deliverysl.luxurydelivery.category.exception.ProtectedCategoryException;
import com.deliverysl.luxurydelivery.category.mapper.CategoryMapper;
import com.deliverysl.luxurydelivery.category.model.Category;
import com.deliverysl.luxurydelivery.category.repository.CategoryRepository;
import com.deliverysl.luxurydelivery.restaurant.model.Restaurant;
import com.deliverysl.luxurydelivery.restaurant.service.RestaurantService;
import com.deliverysl.luxurydelivery.utils.BaseServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService extends BaseServiceImpl<Category,Long> {

    private final RestaurantService restaurantService;
    private final CategoryMapper categoryMapper;
    private final CategoryRepository categoryRepository;

    public Category findByIdOrThrow(Long id){
        return findOptionalById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Transactional
    public Category create (CategoryCreateDTO categoryCreateDTO){

        Restaurant restaurant = restaurantService.findByIdOrThrow(categoryCreateDTO.idRestaurant());
        return categoryMapper.toEntity(categoryCreateDTO,restaurant);
    }

    @Transactional
    public Category edit (CategoryCreateDTO categoryCreateDTO, Long id){

        Category category = findByIdOrThrow(id);
        //Se comprueba si el valor del atributo es true y si lo es salta la excepción
        if (category.isNoCategory()){throw new ProtectedCategoryException(id);}
        //He pensado que cuando modificas una categoría no la puedas cambiar de restaurante
        //Ya que entiendo que el dueño no cambiara una categoria a otro restaurante
        //Restaurant restaurant = restaurantService.findByIdOrThrow(categoryCreateDTO.idRestaurant());
        //category.setRestaurant(restaurant);
        category.setName(categoryCreateDTO.name());
        category.setDescription(categoryCreateDTO.description());
        return save(category);

    }

    @Transactional
    public void deactivateCategoryById(Long id){

        Category category = findByIdOrThrow(id);
        if (category.isNoCategory()){throw new ProtectedCategoryException(id);}

        //Buscamos la categoría por defecto
        Category noCategory = categoryRepository.findByRestaurantIdAndNoCategoryTrue(category.getRestaurant().getId())
                .orElseThrow();

        //Asignamos a los productos la nueva categoría
        category.getProductList().forEach(product -> product.setNoCategory(noCategory));
        deactivate(id);

    }

}
