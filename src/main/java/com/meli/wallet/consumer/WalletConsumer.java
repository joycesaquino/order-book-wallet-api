package com.meli.wallet.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.wallet.dto.OperationsDto;
import com.meli.wallet.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class WalletConsumer {

    WalletService service;

    @SqsListener(value = "${amazon.wallet.integration.queue}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void onSqsEvent(final @Payload String body) throws JsonProcessingException {
        log.info("Received message on wallet consumer {}", body);
        var operations = new ObjectMapper().readValue(body, OperationsDto.class);
        service.save(operations);
    }

}