package com.meli.wallet.controller;

import com.meli.wallet.dto.OperationDto;
import com.meli.wallet.service.WalletService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService service;

    public WalletController(WalletService service) {
        this.service = service;
    }

    @PatchMapping
    public ResponseEntity<?> update(@Valid @RequestBody final OperationDto dto) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}