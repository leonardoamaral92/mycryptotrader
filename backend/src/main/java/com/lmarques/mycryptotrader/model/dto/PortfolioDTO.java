package com.lmarques.mycryptotrader.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PortfolioDTO{
    private Long investorId;
    private Long id;
    private String name;
}
