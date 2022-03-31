package com.meli.wallet.controller;

import com.meli.wallet.dto.OperationDto;
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
    public ResponseEntity<?> update(@Valid @RequestBody final OperationDto dto) {
        log.info("Operation requested with body {}", dto);
        service.save(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}