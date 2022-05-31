package com.lmarques.mystocktrader.controllers;

import com.lmarques.mystocktrader.model.dto.PortfolioResume;
import com.lmarques.mystocktrader.services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/portfolios")
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    @GetMapping("/{userId}")
    public PortfolioResume resumePortfolio(@PathVariable Long userId){
        return portfolioService.generateResume(userId);
    }

}
