package com.meli.wallet.converter;

import com.meli.wallet.dto.OperationDto;
import com.meli.wallet.model.Audit;
import com.meli.wallet.model.wallet.Wallet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.Function;

import static java.time.ZoneOffset.UTC;

@Slf4j
@Component
public class OperationConverter implements Function<OperationDto, Wallet> {

    @Override
    public Wallet apply(OperationDto dto) {
        String API = "API";

        var audit = new Audit();
        audit.setCreatedAt(ZonedDateTime.now(UTC));
        audit.setUpdatedAt(ZonedDateTime.now(UTC));
        audit.setUpdatedBy(API);

        var wallet = new Wallet();
        wallet.setId(UUID.randomUUID());
        wallet.setUserId(dto.getUserId());
        wallet.setQuantity(dto.getQuantity());
        wallet.setValue(dto.getValue());
        wallet.setTraceId(dto.getTraceId());
        wallet.setAudit(audit);
        return wallet;
    }

}
