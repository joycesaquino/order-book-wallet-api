package com.meli.wallet.consumer;

import com.meli.wallet.service.WalletService;
import org.springframework.stereotype.Component;

@Component
public class WalletConsumer {

    WalletService service;

    public void onSqsEvent(final String body){}

}
