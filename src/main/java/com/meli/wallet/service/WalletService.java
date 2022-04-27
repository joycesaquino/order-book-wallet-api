package com.meli.wallet.service;

import com.amazonaws.services.dynamodbv2.datamodeling.TransactionWriteRequest;
import com.meli.wallet.converter.OperationConverter;
import com.meli.wallet.dto.OperationDto;
import com.meli.wallet.dto.OperationsDto;
import com.meli.wallet.model.wallet.Wallet;
import com.meli.wallet.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class WalletService {

    private final OperationConverter converter;
    private final WalletRepository repository;

    public WalletService(OperationConverter converter, WalletRepository repository) {
        this.converter = converter;
        this.repository = repository;
    }

    public Optional<Wallet> findById(Long userId) {
        var wallet = new Wallet();
        wallet.setUserId(userId);
        return repository.findById(wallet);
    }

    public void create(OperationDto dto) {
        repository.save(converter.apply(dto));
    }

    public void save(OperationsDto dtos) {

        var operations = dtos.getOperations();
        var transactions = operations.
                stream()
                .map(transaction -> repository.findById(converter.apply(transaction)));

        TransactionWriteRequest transactionWriteRequest = new TransactionWriteRequest();
        transactions.forEach(transaction -> {

            assert transaction.isPresent();
            var optionalDto = operations.
                    stream().
                    filter(operation -> operation.getUserId().equals(transaction.get().getUserId()))
                    .findFirst();

            assert optionalDto.isPresent();
            var dto = optionalDto.get();
            var operationQuantity = dto.getQuantity();
            var operationValue = dto.getValue();

            switch (dto.getOperationType()) {
                case SALE -> transaction.get().credit(operationValue, operationQuantity);
                case BUY -> transaction.get().debit(operationValue, operationQuantity);
            }

            transactionWriteRequest.addUpdate(transaction.get());
        });

        repository.executeTransactionWrite(transactionWriteRequest);
    }

}