package com.meli.wallet.service;

import com.meli.wallet.dto.OperationDto;
import com.meli.wallet.enums.Type;
import com.meli.wallet.model.Audit;
import com.meli.wallet.model.wallet.Wallet;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

import static java.time.ZoneOffset.UTC;

public class WalletTestFixture {

    public static OperationDto getOperationDto(Type operationType, String userId) {

        var dto = new OperationDto();
        dto.setUserId(Long.parseLong(userId));
        dto.setValue(BigDecimal.TEN);
        dto.setQuantity(1);
        dto.setTraceId(UUID.randomUUID());
        dto.setOperationType(operationType);

        return dto;
    }

    public static Wallet getWallet(String userId) {
        String API = "API";

        var audit = new Audit();
        audit.setCreatedAt(ZonedDateTime.now(UTC));
        audit.setUpdatedAt(ZonedDateTime.now(UTC));
        audit.setUpdatedBy(API);

        var wallet = new Wallet();
        wallet.setUserId(Long.parseLong(userId));
        wallet.setValue(BigDecimal.TEN);
        wallet.setQuantity(1);
        wallet.setTraceId(UUID.randomUUID());
        wallet.setAudit(audit);

        return wallet;
    }

}
