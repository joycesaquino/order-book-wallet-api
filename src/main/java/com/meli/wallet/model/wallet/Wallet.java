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


    public void credit(Wallet operation) {
        var operationValue = operation.getValue();
        var operationQuantity = operation.getQuantity();

        var total = operationValue.multiply(BigDecimal.valueOf(operationQuantity));

        this.setQuantity(operation.getQuantity() + quantity);
        this.setValue(operation.getValue().add(total));

    }

    public void debit(Wallet operation) {
        var operationValue = operation.getValue();
        var operationQuantity = operation.getQuantity();

        var total = operationValue.multiply(BigDecimal.valueOf(operationQuantity));

        this.setQuantity(operation.getQuantity() - quantity);
        this.setValue(operation.getValue().subtract(total));
    }

}
