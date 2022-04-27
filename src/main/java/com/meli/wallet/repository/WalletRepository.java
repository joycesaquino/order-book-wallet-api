package com.meli.wallet.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.amazonaws.services.dynamodbv2.model.*;
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
        try {
            walletMapper.transactionWrite(transactionWriteRequest);
        } catch (TransactionCanceledException tce) {
            System.err.println("Transaction Canceled, implies a client issue, fix before retrying. Error: " + tce.getCancellationReasons());
        } catch (Exception ex) {
            System.err.println("An exception occurred, investigate and configure retry strategy. Error: " + ex.getMessage());
        }
    }

    public void save(Wallet wallet) {
        walletMapper.save(wallet);
    }

}
