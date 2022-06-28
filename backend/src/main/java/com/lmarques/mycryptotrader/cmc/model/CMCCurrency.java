package com.lmarques.mycryptotrader.cmc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMCCurrency {
    private Double price;
    private Double market_cap;
}
