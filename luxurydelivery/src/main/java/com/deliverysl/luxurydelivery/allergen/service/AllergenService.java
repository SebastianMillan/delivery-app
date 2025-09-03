package com.deliverysl.luxurydelivery.allergen.service;

import com.deliverysl.luxurydelivery.allergen.dto.CreateAllergenDTO;
import com.deliverysl.luxurydelivery.allergen.exception.AllergenNotFoundException;
import com.deliverysl.luxurydelivery.allergen.mapper.AllergenMapper;
import com.deliverysl.luxurydelivery.allergen.model.Allergen;
import com.deliverysl.luxurydelivery.allergen.repository.AllergenRepository;
import com.deliverysl.luxurydelivery.product.model.Product;
import com.deliverysl.luxurydelivery.utils.BaseServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AllergenService extends BaseServiceImpl<Allergen, Long> {

    private final AllergenMapper mapper;
    private final AllergenRepository allergenRepository;

    public Allergen findByIdOrThrow(Long id){
        return findOptionalById(id)
                .orElseThrow(() -> new AllergenNotFoundException(id));
    }

    @Transactional
    public Allergen create(CreateAllergenDTO createAllergenDTO){
        return save(mapper.toEntity(createAllergenDTO));
    }

    @Transactional
    public Allergen edit(Long id, CreateAllergenDTO createAllergenDTO){
        return findOptionalById(id)
                .map(allergen -> {
                    allergen.setName(createAllergenDTO.name());
                    allergen.setDescription(createAllergenDTO.description());
                    allergen.setImage(createAllergenDTO.image());
                    return save(allergen);
                }).orElseThrow(() -> new AllergenNotFoundException(id));
    }

    @Transactional
    public void deleteAllergenById(Long id){
        Allergen allergen = findByIdOrThrow(id);
        // Desacomplamos con los helper los productos asociados a este alergeno
        for (Product p : new ArrayList<>(allergen.getProductList())) {
            p.removeAllergen(allergen);
        }
        allergen.setActivate(false);
        save(allergen);
    }

    @Transactional
    public Allergen activateAllergen(Long id){
        Allergen allergen = findByIdOrThrow(id);
        if (!allergen.isActivate()){
            allergen.setActivate(true);
            save(allergen);
        }
        return allergen;
    }

    public List<Allergen> findByActivateTrue(){
        return allergenRepository.findByActivateTrue();
    }
    public List<Allergen> findByActivateFalse(){
        return allergenRepository.findByActivateFalse();
    }
}
