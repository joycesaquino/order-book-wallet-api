package com.meli.wallet.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.InternalServerErrorException;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.services.dynamodbv2.model.TransactionCanceledException;
import com.meli.wallet.model.wallet.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WalletRepository {

    private final DynamoDBMapper walletMapper;
    private final QueryFactory query;

    public Optional<Wallet> findById(Wallet wallet) {
        return walletMapper.query(Wallet.class, query.findById(wallet)).stream().findFirst();
    }

    public void executeTransactionWrite(TransactionWriteRequest transactionWriteRequest) {
        walletMapper.transactionWrite(transactionWriteRequest);
    }

}
