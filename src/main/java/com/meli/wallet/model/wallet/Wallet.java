package com.meli.wallet.model.wallet;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.meli.wallet.model.Audit;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@DynamoDBTable(tableName = "wallet")
public class Wallet {

    @DynamoDBRangeKey(attributeName = "id")
    private UUID id;

    @DynamoDBHashKey
    private Long userId;
    private UUID traceId;
    private Integer quantity;
    private BigDecimal value;
    private Audit audit;


    public void credit(BigDecimal operationValue, Integer operationQuantity) {

        var total = operationValue.multiply(BigDecimal.valueOf(operationQuantity));

        this.quantity = quantity + operationQuantity;
        this.value = value.add(total);

    }

    public void debit(BigDecimal operationValue, Integer operationQuantity) {
        var total = operationValue.multiply(BigDecimal.valueOf(operationQuantity));

        this.quantity = quantity - operationQuantity;
        this.value = value.subtract(total);
    }

}
