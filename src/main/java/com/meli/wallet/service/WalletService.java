package com.meli.wallet.service;

import com.meli.wallet.converter.OperationConverter;
import com.meli.wallet.dto.OperationDto;
import com.meli.wallet.enums.Type;
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

            var operationType = dto.getOperationType();
            var value = dto.getValue();
            var quantity = dto.getQuantity();

            var total = value.multiply(BigDecimal.valueOf(quantity));

            wallet.setId(findWallet.getId());

            if (operationType.equals(Type.SALE)) {
                wallet.setQuantity(findWallet.getQuantity() + quantity);
                wallet.setValue(findWallet.getValue().add(total));
            } else {
                wallet.setQuantity(findWallet.getQuantity() - quantity);
                wallet.setValue(findWallet.getValue().subtract(total));
            }

            repository.save(wallet);
        }, () -> repository.save(wallet));

    }
}
