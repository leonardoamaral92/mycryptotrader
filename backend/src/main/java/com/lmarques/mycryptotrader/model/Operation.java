package com.lmarques.mycryptotrader.model;

import com.lmarques.mycryptotrader.model.dto.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "ct_operation")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;
    private Long coinId;
    private String coinName;
    private String coinSymbol;
    private Double coinPrice;
    private LocalDate date;
    private OperationType type;
    private Double qtdCoin;
    private Double totalValue;

    public Operation(Order order, Portfolio portfolio, OperationType type) {
        this.portfolio = portfolio;
        coinId = order.getCoinId();
        coinName = order.getCoinName();
        coinSymbol = order.getCoinSymbol();
        coinPrice = order.getCoinPrice();
        date = order.getDate();
        this.type = type;
        qtdCoin = order.getQtdCoin();
        totalValue = order.getTotalValue();
    }

    public Double getQtdByOperationType(){
        return OperationType.BUY.equals(type) ? qtdCoin : -qtdCoin;
    }

    public Double getValueByOperationType(){
        return OperationType.BUY.equals(type) ? totalValue : -totalValue;
    }
}
