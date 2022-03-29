package com.meli.wallet.converter;

import com.meli.wallet.dto.OperationDto;
import com.meli.wallet.enums.Type;
import com.meli.wallet.model.wallet.Wallet;
import org.springframework.stereotype.Component;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.function.Function;

@Component
public class OperationConverter implements Function<OperationDto, Wallet> {

    @Override
    public Wallet apply(OperationDto dto) {
        var wallet = new Wallet();
        wallet.setUserId(dto.getUserId());
        wallet.setQuantity(dto.getQuantity());
        wallet.setValue(dto.getValue());
        wallet.setTraceId(dto.getTraceId());
        return wallet;
    }

}
