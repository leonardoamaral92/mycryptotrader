package com.lmarques.mystocktrader.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositRequest {

    private Long userId;
    private Double depositValue;
}
