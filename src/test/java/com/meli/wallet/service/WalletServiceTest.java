package com.meli.wallet.service;

import com.amazonaws.services.dynamodbv2.datamodeling.TransactionWriteRequest;
import com.meli.wallet.converter.OperationConverter;
import com.meli.wallet.dto.OperationDto;
import com.meli.wallet.dto.OperationsDto;
import com.meli.wallet.enums.Type;
import com.meli.wallet.model.wallet.Wallet;
import com.meli.wallet.repository.WalletRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class WalletServiceTest {

    @InjectMocks
    WalletService service;

    @Mock
    WalletRepository repository;

    @Mock
    OperationConverter converter;

    @Test
    void ShouldSaveWalletOperations() {
        var saleUserId = "1234";
        var buyUserId = "12345";

        var saleOperationDto = WalletTestFixture.getOperationDto(Type.SALE, saleUserId);
        var buyOperationDto = WalletTestFixture.getOperationDto(Type.BUY, buyUserId);

        var transactions = new ArrayList<OperationDto>();
        transactions.add(saleOperationDto);
        transactions.add(buyOperationDto);

        var operations = new OperationsDto();
        operations.setOperations(transactions);

//        var firstWallet = WalletTestFixture.getWallet(saleUserId);
//        var secondWallet = WalletTestFixture.getWallet(buyUserId);
//
//        Mockito.when(converter.apply(saleOperationDto)).thenReturn(firstWallet);
//        Mockito.when(converter.apply(saleOperationDto)).thenReturn(secondWallet);
//
//        Mockito.when(repository.findById(firstWallet)).thenReturn(Optional.of(firstWallet));
//        Mockito.when(repository.findById(secondWallet)).thenReturn(Optional.of(secondWallet));
//
//        TransactionWriteRequest transactionWriteRequest = new TransactionWriteRequest();
//        transactionWriteRequest.addUpdate(firstWallet);
//        transactionWriteRequest.addUpdate(secondWallet);

        Assertions.assertDoesNotThrow(() -> service.save(operations));

//        Mockito.verify(repository).executeTransactionWrite(transactionWriteRequest);
    }

}
