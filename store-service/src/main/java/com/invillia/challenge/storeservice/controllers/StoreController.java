package com.invillia.challenge.storeservice.controllers;

import com.invillia.challenge.storeservice.entities.Store;
import com.invillia.challenge.storeservice.mappers.TransformerMapper;
import com.invillia.challenge.storeservice.models.StoreDTO;
import com.invillia.challenge.storeservice.services.StoreService;
import com.invillia.challenge.storeservice.utils.SortUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

/**
 * Created by eriveltonfacundo on 05/04/2019.
 */

@RestController
@RequestMapping("/v1/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;
    @Autowired
    private TransformerMapper transformerMapper;

    @GetMapping
    public ResponseEntity<Page<StoreDTO>> findAll(@RequestParam(name = "filter", required = false) String filter,
                                                  @RequestParam(name = "sort", defaultValue = "id") String sort,
                                                  @RequestParam(name = "page", defaultValue = "0") Integer page,
                                                  @RequestParam(name = "size", defaultValue = "100") Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size, SortUtil.toSort(sort));
        Page<Store> stores = storeService.findAll(filter, sort, pageRequest);
        if (!stores.hasContent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        PageImpl<StoreDTO> storeDTOS = new PageImpl<>(transformerMapper.storetoStoreDTO(stores.getContent()), pageRequest, stores.getTotalElements());
        return new ResponseEntity<>(storeDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDTO> findById(@PathVariable("id") Long id) {
        Store store = storeService.findById(id);
        if (store == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(transformerMapper.storeDTOtoStore(store), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid StoreDTO store, UriComponentsBuilder uriBuilder) {
        if (storeService.exists(store.getId()))
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        Store storeSaved = transformerMapper.storeDTOtoStore(store);
        storeService.save(storeSaved);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriBuilder.path("/v1/stores/{id}").buildAndExpand(storeSaved.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody @Valid StoreDTO store) {
        if (!storeService.exists(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        storeService.update(transformerMapper.storeDTOtoStore(store));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        if (!storeService.exists(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        storeService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
