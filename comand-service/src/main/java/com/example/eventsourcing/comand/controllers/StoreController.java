package com.example.eventsourcing.comand.controllers;

import com.example.eventsourcing.comand.entities.Store;
import com.example.eventsourcing.comand.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/v1/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid Store model, UriComponentsBuilder uriBuilder) {
        if (storeService.findById(model.getId()).isPresent())
            return new ResponseEntity<>(CONFLICT);

        Store store = storeService.save(model);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/v1/stores/{id}").buildAndExpand(store.getId()).toUri());
        return new ResponseEntity<>(headers, CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody @Valid Store model) {
        if (!storeService.findById(model.getId()).isPresent())
            return new ResponseEntity<>(NOT_FOUND);

        storeService.save(model);

        return new ResponseEntity<>(NO_CONTENT);
    }
}
