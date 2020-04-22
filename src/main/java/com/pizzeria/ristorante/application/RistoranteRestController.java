package com.pizzeria.ristorante.application;

import com.pizzeria.ristorante.domain.RistoranteService;
import com.pizzeria.ristorante.infrastructure.menu.MenuEntryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RistoranteRestController {

    private final RistoranteService ristoranteService;

    @PostMapping("api/orders")
    public ResponseEntity<Object> orderNewPizza(@RequestBody OrderDto order) {
        log.info("Received order " + order.getPizzaOrders());
        ristoranteService.order(order);
        return ResponseEntity.created(URI.create("")).build();
    }

    @GetMapping("api/menu")
    public Set<MenuEntryDto> getMenuEntries() {
        return ristoranteService.getMenu();
    }

}
