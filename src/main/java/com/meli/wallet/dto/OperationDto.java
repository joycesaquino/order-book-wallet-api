package com.meli.wallet.dto;
import com.meli.wallet.enums.Type;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@ToString
public class OperationDto {

    @NotNull(message = "User Id cannot be null")
    private Long userId;

    @NotNull(message = "Trace Id cannot be null")
    private UUID traceId;

    @DecimalMin(value = "1" , message = "The minimun value for quantity is one operation")
    private Integer quantity;

    @DecimalMin(value = "0.1", message = "Cannot accept operations with price zero")
    private BigDecimal value;

    @NotNull(message = "Operation type cannot be null")
    private Type operationType;

}
