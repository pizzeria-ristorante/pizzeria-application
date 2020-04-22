package com.pizzeria.ristorante.application;

import com.pizzeria.ristorante.domain.RistoranteService;
import com.pizzeria.ristorante.infrastructure.menu.MenuEntryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/orders")
@Slf4j
public class RistoranteRestController {

    private final RistoranteService ristoranteService;

    @GetMapping
    public Set<MenuEntryDto> orderNewPizza(@RequestParam String name) {
        return ristoranteService.order(name);
    }

}
