package com.meli.wallet.model.wallet;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.meli.wallet.enums.Type;
import com.meli.wallet.model.Audit;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@DynamoDBTable(tableName = "wallet")
public class Wallet extends Audit {

    @DynamoDBRangeKey
    private Long id;

    @DynamoDBHashKey
    private Long userId;
    private UUID traceId;
    private Integer quantity;
    private BigDecimal value;

}
