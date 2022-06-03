package com.lmarques.mystocktrader.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PortfolioDTO{
    private Long userId;
    private Long id;
    private String name;
}
