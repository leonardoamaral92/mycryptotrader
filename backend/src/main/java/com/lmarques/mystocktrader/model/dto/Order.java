package com.lmarques.mystocktrader.model.dto;

import com.lmarques.mystocktrader.model.OperationType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Order {

    private Long userId;
    private OrderStatus status;
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
