package com.meli.wallet.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.meli.wallet.model.wallet.Wallet;
import org.springframework.stereotype.Component;



@Component
public class QueryFactory {

    public DynamoDBQueryExpression<Wallet> findById(Wallet wallet) {
        return new DynamoDBQueryExpression<Wallet>()
                .withHashKeyValues(wallet);
    }

}
