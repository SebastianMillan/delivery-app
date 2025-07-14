package com.deliverysl.luxurydelivery.category.service;

import com.deliverysl.luxurydelivery.category.dto.CategoryCreateDTO;
import com.deliverysl.luxurydelivery.category.exception.CategoryNotFoundException;
import com.deliverysl.luxurydelivery.category.mapper.CategoryMapper;
import com.deliverysl.luxurydelivery.category.model.Category;
import com.deliverysl.luxurydelivery.category.repository.CategoryRepository;
import com.deliverysl.luxurydelivery.restaurant.model.Restaurant;
import com.deliverysl.luxurydelivery.restaurant.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final RestaurantService restaurantService;
    private final CategoryMapper categoryMapper;


    public List<Category> findAll(){
            return categoryRepository.findAll();
    }

    public Category findById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public Category create (CategoryCreateDTO categoryCreateDTO){

        Restaurant restaurant = restaurantService.findById(categoryCreateDTO.idRestaurant());

        return categoryRepository.save(categoryMapper.toEntity(categoryCreateDTO,restaurant));
    }

    public Category edit (CategoryCreateDTO categoryCreateDTO, Long id){

        Restaurant restaurant = restaurantService.findById(categoryCreateDTO.idRestaurant());

        return categoryRepository.findById(id)
                .map(c -> {
                            c.setName( categoryCreateDTO.name());
                            c.setDescription( categoryCreateDTO.description());
                            c.setRestaurant(restaurant);
                            //c.setProductList(category.getProductList());
                            return categoryRepository.save(c);
                })
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public void delete (Long id){
        categoryRepository.deleteById(id);
    }


}
