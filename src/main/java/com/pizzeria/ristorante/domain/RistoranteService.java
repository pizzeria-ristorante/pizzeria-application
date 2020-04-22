package com.pizzeria.ristorante.domain;

import com.pizzeria.ristorante.application.OrderDto;
import com.pizzeria.ristorante.infrastructure.IngredientDto;
import com.pizzeria.ristorante.infrastructure.menu.MenuEntryDto;
import com.pizzeria.ristorante.infrastructure.menu.MenuServiceClient;
import com.pizzeria.ristorante.infrastructure.stock.StockServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class RistoranteService {

    private final StockServiceClient stockServiceClient;
    private final MenuServiceClient menuServiceClient;

    public void order(OrderDto order) {
        Map<String, Integer> pizzaToQuantity = new HashMap<>();
        order.getPizzaOrders().forEach(pizzaOrder -> {
            if (pizzaToQuantity.containsKey(pizzaOrder)) {
                pizzaToQuantity.put(pizzaOrder, pizzaToQuantity.get(pizzaOrder) + 1);
            } else {
                pizzaToQuantity.put(pizzaOrder, 1);
            }
        });

        for (Map.Entry<String, Integer> entry : pizzaToQuantity.entrySet()) {
            String pizzaName = entry.getKey();
            int quantity = entry.getValue();
            log.info("Got an order for {} {} Pizzas.", quantity, pizzaName);
            Set<MenuEntryDto> allMenuEntries = menuServiceClient.getAllMenuEntries(pizzaName);
            log.info("Found {} menu entries for {}", allMenuEntries.size(), pizzaName);
            if (allMenuEntries.size() != 1) {
                throw new IllegalStateException("There are multiple pizzas with name " + pizzaName + " in the menu!");
            }
            MenuEntryDto pizzaMenu = allMenuEntries.iterator().next();
            Set<IngredientDto> ingredients = pizzaMenu.getIngredients();
            for (IngredientDto ingredient : ingredients) {
                Set<IngredientDto> allIngredientsInStock = stockServiceClient.getAllIngredientsInStock(ingredient.getName());
                if (allIngredientsInStock.size() != 1) {
                    throw new IllegalStateException("There are multiple Ingredients with name " + ingredient.getName() + " in stock!");
                }
                int requiredIngredientQuantity = ingredient.getQuantity() * quantity;
                IngredientDto ingredientStockInfo = allIngredientsInStock.iterator().next();
                log.info("Found {} {} in stock", ingredient.getName(), ingredientStockInfo.getQuantity());
                if (requiredIngredientQuantity > ingredientStockInfo.getQuantity()) {
                    throw new IllegalStateException("Not enough " + ingredient.getName() + " in stock!");
                }
                stockServiceClient.deleteByName(new IngredientDto(ingredient.getName(), ingredient.getQuantity() * quantity));
            }
        }
    }

    public Set<MenuEntryDto> getMenu() {
        return menuServiceClient.getAllMenuEntries(null);
    }
}
