package com.deliverysl.luxurydelivery.category.service;

import com.deliverysl.luxurydelivery.category.dto.CategoryCreateDTO;
import com.deliverysl.luxurydelivery.category.exception.CategoryNotFoundException;
import com.deliverysl.luxurydelivery.category.mapper.CategoryMapper;
import com.deliverysl.luxurydelivery.category.model.Category;
import com.deliverysl.luxurydelivery.product.model.Product;
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
    //private final CategoryRepository categoryRepository;

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
    public void deactivateCategoryById(Long id){

        //No se como podriamos buscar la categoría por defecto de cada restaurante,
        //ya que el id cambia para cada restaurante.Lo he buscado por el nombre,se que no es lo mas correcto
        //Pero por ahora funciona
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

            save(noCategory);
            deactivate(id);
        }

    }

}
