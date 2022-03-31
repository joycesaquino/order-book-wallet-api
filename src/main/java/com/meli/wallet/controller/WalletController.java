package com.meli.wallet.controller;

import com.meli.wallet.dto.OperationsDto;
import com.meli.wallet.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService service;

    public WalletController(WalletService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> update(@Valid @RequestBody final OperationsDto dto) {
        service.save(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> find(@Valid @PathVariable final Long userId) {
        return ResponseEntity.ok(service.findById(userId));
    }
}