package com.mehrdad.reviewproduct.controller;

import com.mehrdad.reviewproduct.dto.ProviderDto;
import com.mehrdad.reviewproduct.mapper.ProviderMapper;
import com.mehrdad.reviewproduct.model.Provider;
import com.mehrdad.reviewproduct.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by m.peykari on 2/15/2021.
 */
@RestController
@RequestMapping("/api/provider")
public class ProviderController {

    private final ProviderMapper mapper;
    private final ProviderService service;

    @Autowired
    public ProviderController(ProviderMapper mapper, ProviderService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<ProviderDto> addProvider(@RequestBody ProviderDto providerDto){
        Provider provider = mapper.toProvider(providerDto);
        final Provider savedProvider = service.save(provider);
        final ProviderDto retrieve = mapper.toProviderDto(savedProvider);
        return ResponseEntity.ok(retrieve);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProviderDto> getById(@PathVariable Long id) {
        final Provider provider = service.getById(id);
        final ProviderDto providerDto = mapper.toProviderDto(provider);
        return ResponseEntity.ok(providerDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProviderDto> update(@PathVariable Long id,@RequestBody ProviderDto providerDto){
        Provider provider = mapper.toProvider(providerDto);
        final Provider savedProvider = service.update(id,provider);
        final ProviderDto retrieve = mapper.toProviderDto(savedProvider);
        return ResponseEntity.ok(retrieve);
    }

}
