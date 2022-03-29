package com.meli.wallet.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.meli.wallet.model.wallet.Wallet;
import org.springframework.stereotype.Component;



@Component
public class QueryFactory {

    public DynamoDBSaveExpression saveWith(String hash) {
        var expression = new DynamoDBSaveExpression();
        expression.withExpectedEntry("hash",
                new ExpectedAttributeValue(new AttributeValue(hash)).withComparisonOperator(ComparisonOperator.NE));
        return expression;
    }

    public DynamoDBQueryExpression<Wallet> findById(Wallet wallet) {
        return new DynamoDBQueryExpression<Wallet>()
                .withHashKeyValues(wallet);
    }

}
