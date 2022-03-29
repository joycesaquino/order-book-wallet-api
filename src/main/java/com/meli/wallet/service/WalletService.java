package com.meli.wallet.service;

import com.meli.wallet.converter.OperationConverter;
import com.meli.wallet.dto.OperationDto;
import com.meli.wallet.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
        var find = repository.findById(converter.apply(dto));
        find.stream().findFirst().ifPresentOrElse(findWallet -> {

            findWallet.setQuantity(findWallet.getQuantity() + wallet.getQuantity());
            var total = dto.getValue().multiply(BigDecimal.valueOf(dto.getQuantity()));
            findWallet.setValue(findWallet.getValue().add(total));

        }, () -> repository.save(wallet));

    }
}
