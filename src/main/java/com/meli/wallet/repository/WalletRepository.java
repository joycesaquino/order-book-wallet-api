package com.meli.wallet.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.meli.wallet.model.wallet.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WalletRepository {

    private final DynamoDBMapper operationMapper;
    private final QueryFactory query;

    public void save(Wallet wallet) {

        try {
            operationMapper.save(wallet);
        } catch (ConditionalCheckFailedException ignore) {
        }
    }

}
