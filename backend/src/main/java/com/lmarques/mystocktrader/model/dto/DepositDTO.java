package com.lmarques.mystocktrader.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DepositDTO {

    private Long userId;
    private Double depositValue;
    private Double newFunds;
}
