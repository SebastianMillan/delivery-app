package com.deliverysl.luxurydelivery.category.service;

import com.deliverysl.luxurydelivery.category.dto.CategoryCreateDTO;
import com.deliverysl.luxurydelivery.category.exception.CategoryNotFoundException;
import com.deliverysl.luxurydelivery.category.mapper.CategoryMapper;
import com.deliverysl.luxurydelivery.category.model.Category;
import com.deliverysl.luxurydelivery.category.repository.CategoryRepository;
import com.deliverysl.luxurydelivery.product.model.Product;
import com.deliverysl.luxurydelivery.restaurant.model.Restaurant;
import com.deliverysl.luxurydelivery.restaurant.service.RestaurantService;
import com.deliverysl.luxurydelivery.utils.BaseServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

        Restaurant restaurant = restaurantService.findByIdOrThrow(categoryCreateDTO.idRestaurant());
        Category category = findByIdOrThrow(id);

        category.setName(categoryCreateDTO.name());
        category.setDescription(categoryCreateDTO.description());
        category.setRestaurant(restaurant);

        return save(category);

    }

    @Transactional
    public void deleteCategoryById(Long id){

        Category category = findByIdOrThrow(id);
        if (category.getName().trim().equalsIgnoreCase("Sin categoría")){
            //Crear una excepción específica para ello
            throw new CategoryNotFoundException(id);
        }
        else{
            //Buscamos la categoría por defecto
            Category noCategory = category.getRestaurant()
                    .getCategoryList()
                    .stream()
                    .filter(cat-> cat.getName().equalsIgnoreCase("Sin categoría"))
                    .findFirst()
                    .orElseThrow(() -> new CategoryNotFoundException(id));

            //Asignamos a los productos la nueva categoría
            for (Product product: category.getProductList()){
                product.setCategory(noCategory);
            }

            //Copiamos todos los productos a la lista por defecto y limpiamos la lista de la categoría encontrada
            noCategory.getProductList().addAll(category.getProductList());
            category.getProductList().clear();

            category.setActivate(false);
            save(category);
            save(noCategory);
        }

    }

    @Transactional
    public Category activate(Long id){
        Category category = findByIdOrThrow(id);
        if (!category.isActivate()){
            category.setActivate(true);
            save(category);
        }
        return category;
    }

    public List<Category> findByActivateTrue(){
        return categoryRepository.findByActivateTrue();
    }

    public List<Category> findByActivateFalse(){
        return categoryRepository.findByActivateFalse();
    }

}
