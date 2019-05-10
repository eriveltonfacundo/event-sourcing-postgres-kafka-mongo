package com.example.eventsourcing.comand.controllers;

import com.example.eventsourcing.comand.entities.Store;
import com.example.eventsourcing.comand.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid Store model, UriComponentsBuilder uriBuilder) {
        Store store = storeService.findById(model.getId());
        if (store != null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        model.setId(null);
        storeService.save(model);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/v1/stores/{id}").buildAndExpand(model.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody @Valid Store model) {
        Store store = storeService.findById(model.getId());
        if (store == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        storeService.save(model);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
