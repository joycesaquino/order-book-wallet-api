package com.meli.wallet.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.meli.wallet.model.wallet.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WalletRepository {

    private final DynamoDBMapper walletMapper;
    private final QueryFactory query;

    public List<Wallet> findById(Wallet wallet) {
        return walletMapper.query(Wallet.class, query.findById(wallet));
    }

    public void save(Wallet wallet) {
        try {
            walletMapper.save(wallet);
        } catch (ConditionalCheckFailedException ignore) {
        }
    }

}
