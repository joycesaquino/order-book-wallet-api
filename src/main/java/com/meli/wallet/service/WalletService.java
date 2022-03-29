package com.meli.wallet.service;

import com.meli.wallet.converter.OperationConverter;
import com.meli.wallet.dto.OperationDto;
import com.meli.wallet.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private final OperationConverter converter;
    private final WalletRepository repository;

    public WalletService(OperationConverter converter, WalletRepository repository) {
        this.converter = converter;
        this.repository = repository;
    }

    public void save(OperationDto dto) {
        var wallet = converter.apply(dto);
        repository.save(wallet);
    }
}
