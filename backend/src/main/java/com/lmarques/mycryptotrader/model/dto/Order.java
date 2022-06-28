package com.lmarques.mycryptotrader.model.dto;

import com.lmarques.mycryptotrader.model.OperationType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class Order {
    private Long coinId;
    private Long portfolioId;
    private String coinName;
    private String coinSymbol;
    private Double coinPrice;
    private LocalDate date;
    private OperationType type;
    private Double qtdCoin;
    private Double totalValue;
}
