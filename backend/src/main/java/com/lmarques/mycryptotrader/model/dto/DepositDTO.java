package com.lmarques.mycryptotrader.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DepositDTO {

    private Long investorId;
    private Double depositValue;
    private Double newFunds;
}
