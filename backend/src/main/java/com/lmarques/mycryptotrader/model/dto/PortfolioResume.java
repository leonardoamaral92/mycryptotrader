package com.lmarques.mycryptotrader.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class PortfolioResume {

    private Double totalBalance;
    private Double valueAllOperations;
    private Double profitsCash;
    private Double profitsPercent;
    private Double funds;
    private List<OperationResume> operationList;


}
