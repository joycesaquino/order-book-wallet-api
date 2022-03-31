package com.meli.wallet.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class OperationsDto {

    @NotEmpty
    private List<@Valid OperationDto> operations;

}
