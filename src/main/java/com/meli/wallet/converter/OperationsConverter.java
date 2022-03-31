package com.meli.wallet.converter;

import com.meli.wallet.dto.OperationsDto;
import com.meli.wallet.model.wallet.Wallet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Slf4j
@Component
public class OperationsConverter implements Function<OperationsDto, List<Wallet>> {

    OperationConverter operationConverter;

    @Override
    public List<Wallet> apply(OperationsDto dto) {

        return dto.getOperations()
                .parallelStream()
                .map(operation -> operationConverter.apply(operation))
                .collect(Collectors.toList());

    }
}
