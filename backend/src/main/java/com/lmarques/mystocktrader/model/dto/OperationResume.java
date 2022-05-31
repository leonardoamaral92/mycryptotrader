package com.lmarques.mystocktrader.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OperationResume {

    private Long coinId;
    private String coinName;
    private String coinSymbol;
    private Double balance;
    private Double qtdTotalCoin;
    private Double totalValue;
    private Double averagePrice;
    private Double profitsCash;
    private Double profitsPercent;
}
