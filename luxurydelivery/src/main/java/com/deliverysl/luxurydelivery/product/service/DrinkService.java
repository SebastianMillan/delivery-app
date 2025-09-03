package com.deliverysl.luxurydelivery.product.service;

import com.deliverysl.luxurydelivery.allergen.service.AllergenService;
import com.deliverysl.luxurydelivery.category.model.Category;
import com.deliverysl.luxurydelivery.category.service.CategoryService;
import com.deliverysl.luxurydelivery.allergen.model.Allergen;
import com.deliverysl.luxurydelivery.product.model.DrinkSize;
import com.deliverysl.luxurydelivery.product.dto.CreateDrinkDTO;
import com.deliverysl.luxurydelivery.product.exception.ProductNotFoundException;
import com.deliverysl.luxurydelivery.product.mapper.DrinkMapper;
import com.deliverysl.luxurydelivery.product.model.Drink;
import com.deliverysl.luxurydelivery.utils.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrinkService extends BaseServiceImpl<Drink, Long> {

    private final CategoryService categoryService;
    private final AllergenService allergenService;
    private final DrinkMapper mapper;

    /*
    * Transactional controla la gestión de transacciones en la BBDD.
    * Abre una transacción, hace commit si todo va bien o rollback si falla.
    * Es recomendado en métodos de escritura como crear, editar y eliminar.
    * */
    @Transactional
    public Drink create(CreateDrinkDTO createDrinkDTO){
        Category category = categoryService.findByIdOrThrow(createDrinkDTO.categoryId());
        List<Allergen> allergenList = createDrinkDTO.allergensIdList().stream()
                .map(allergenService::findByIdOrThrow)
                .toList();

        Drink drink = mapper.toEntity(createDrinkDTO, category);
        drink.setActivate(true);
        category.addProduct(drink);

        // Agregamos con los helper los alergenos a este Drink
        allergenList.forEach(allergen -> {
            if (allergen.isActivate()){
                drink.addAllergen(allergen);
            }
        });

        return save(drink);
    }

    @Transactional
    public Drink edit(Long id, CreateDrinkDTO createDrinkDTO){
        Category category = categoryService.findByIdOrThrow(createDrinkDTO.categoryId());
        List<Allergen> allergenList = createDrinkDTO.allergensIdList().stream()
                .map(allergenService::findByIdOrThrow)
                .collect(Collectors.toList());

        return findOptionalById(id)
                .map(drink -> {
                    // Desacomplamos anteriores alergenos asociados
                    drink.getAllergensList().forEach(drink::removeAllergen);

                    // Acomplamos los nuevos alergenos
                    allergenList.forEach(drink::addAllergen);

                    drink.setName(createDrinkDTO.name());
                    drink.setDescription(createDrinkDTO.description());
                    drink.setImage(createDrinkDTO.image());
                    drink.setPrice(createDrinkDTO.price());
                    drink.setCategory(category);
                    drink.setAllergensList(allergenList);
                    drink.setAlcoholic(createDrinkDTO.alcoholic());
                    drink.setDrinkSize(DrinkSize.valueOf(createDrinkDTO.drinkSize()));
                    return save(drink);
                }).orElseThrow(() -> new ProductNotFoundException(id));
    }
}
