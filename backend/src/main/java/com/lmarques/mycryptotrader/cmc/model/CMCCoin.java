package com.lmarques.mycryptotrader.cmc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CMCCoin {

    private Long id;
    private String name;
    private String symbol;
    private CMCQuote quote;
    private Integer cmc_rank;

}
