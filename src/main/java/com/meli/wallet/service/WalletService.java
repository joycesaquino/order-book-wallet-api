package com.meli.wallet.service;

import com.amazonaws.services.dynamodbv2.datamodeling.TransactionWriteRequest;
import com.meli.wallet.converter.OperationConverter;
import com.meli.wallet.dto.OperationsDto;
import com.meli.wallet.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class WalletService {

    private final OperationConverter converter;
    private final WalletRepository repository;

    public WalletService(OperationConverter converter, WalletRepository repository) {
        this.converter = converter;
        this.repository = repository;
    }

    public void save(OperationsDto dtos) {

        var operations = dtos.getOperations();

        var transactions = operations.
                stream()
                .map(transaction -> repository.findById(converter.apply(transaction)).get());


        TransactionWriteRequest transactionWriteRequest = new TransactionWriteRequest();

        transactions.forEach(transaction -> {

            var dto = operations.
                    stream().
                    filter(operation -> operation.getUserId().equals(transaction.getUserId()))
                    .findFirst().get();

            var operationType = dto.getOperationType();
            var operationQuantity = dto.getQuantity();
            var operationValue = dto.getValue();

            switch (operationType) {
                case SALE -> transaction.credit(operationValue, operationQuantity);
                case BUY -> transaction.debit(operationValue, operationQuantity);
            }
            transactionWriteRequest.addUpdate(transaction);
        });

        repository.executeTransactionWrite(transactionWriteRequest);
    }

}
