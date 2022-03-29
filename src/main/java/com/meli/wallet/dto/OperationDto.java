package com.meli.wallet.dto;
import com.meli.wallet.enums.Type;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class OperationDto {

    @NotEmpty(message = "User Id cannot be empty")
    private Long userId;

    @NotEmpty(message = "Trace Id cannot be empty")
    private UUID traceId;

    @DecimalMin(value = "1" , message = "The minimun value for quantity is one operation")
    private Integer quantity;

    @DecimalMin(value = "0.1", message = "Cannot accept operations with price zero")
    private BigDecimal value;

    @NotEmpty(message = "Operation type cannot be empty")
    private Type type;

}
