package com.pizzeria.ristorante.infrastructure.menu;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.Set;

@FeignClient(name = "menu-service", url = "${menu-service.url}")
public interface MenuServiceClient {

    @GetMapping("api/menu-card-entries")
    Set<MenuEntryDto> getAllMenuEntries(@RequestParam String name);

    @GetMapping("api/menu-card-entries/{id}")
    ResponseEntity<MenuEntryDto> getbyId(@PathVariable("id") int id);
}
