package com.invillia.challenge.viewservice.controllers;

import com.invillia.challenge.viewservice.repositories.StoreReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by eriveltonfacundo on 06/04/2019.
 */

@RestController
@RequestMapping("/v1/stores")
public class StoreController {

    @Autowired
    private StoreReadRepository storeReadRepository;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(name = "filter", required = false) String filter,
                                    @RequestParam(name = "sort", defaultValue = "id") String sort,
                                    @RequestParam(name = "page", defaultValue = "0") Integer page,
                                    @RequestParam(name = "size", defaultValue = "100") Integer size) {
        return ResponseEntity.ok().body(storeReadRepository.findAll(filter, sort, page, size));
    }
}
