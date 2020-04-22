package com.pizzeria.ristorante.domain;

import com.pizzeria.ristorante.infrastructure.IngredientDto;
import com.pizzeria.ristorante.infrastructure.menu.MenuEntryDto;
import com.pizzeria.ristorante.infrastructure.menu.MenuServiceClient;
import com.pizzeria.ristorante.infrastructure.stock.StockServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class RistoranteService {

    private final StockServiceClient stockServiceClient;
    private final MenuServiceClient menuServiceClient;

    public Set<MenuEntryDto> order(String name) {
        Set<MenuEntryDto> allMenuEntries = menuServiceClient.getAllMenuEntries(name);
        log.info("Found menus for name: {} : {}", name, allMenuEntries.size());
        Set<IngredientDto> allIngredientsInStock = stockServiceClient.getAllIngredientsInStock(Optional.empty());
        log.info("Found ingredients: {}", allIngredientsInStock.size());
        return allMenuEntries;
    }
}
