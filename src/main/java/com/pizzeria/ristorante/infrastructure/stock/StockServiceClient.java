package com.pizzeria.ristorante.infrastructure.stock;

import com.pizzeria.ristorante.infrastructure.IngredientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@FeignClient(name = "stock-service", url = "${stock-service.url}")
public interface StockServiceClient {

    @GetMapping("api/ingredients")
    Set<IngredientDto> getAllIngredientsInStock(@RequestParam String name);

    @GetMapping("api/ingredients/{id}")
    ResponseEntity<IngredientDto> getbyId(@PathVariable("id") int id);

    @DeleteMapping("api/ingredients")
    ResponseEntity<IngredientDto> deleteByName(@RequestBody IngredientDto ingredientDto);
}
