package com.pizzeria.ristorante.infrastructure.menu;

import com.pizzeria.ristorante.infrastructure.IngredientDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuEntryDto {

    private Integer id;
    private String name;
    private Set<IngredientDto> ingredients;
    private double price;

}
