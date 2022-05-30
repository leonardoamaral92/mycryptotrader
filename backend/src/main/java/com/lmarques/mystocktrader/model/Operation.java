package com.lmarques.mystocktrader.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Builder
@Table(name = "ct_operation")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;
    private Long coinId;
    private String coinName;
    private String coinSymbol;
    private Double coinPrice;
    private LocalDate date;
    private OperationType type;
    private Double qtdCoin;
    private Double totalValue;

}
