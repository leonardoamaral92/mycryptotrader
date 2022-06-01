package com.lmarques.mystocktrader.controllers;

import com.lmarques.mystocktrader.model.dto.APIResponse;
import com.lmarques.mystocktrader.model.dto.PortfolioRequest;
import com.lmarques.mystocktrader.model.dto.PortfolioResume;
import com.lmarques.mystocktrader.services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/portfolios")
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    @GetMapping("/{userId}/resume")
    public APIResponse resumePortfolio(@PathVariable Long userId){
        return portfolioService.generateResume(userId);
    }

    @PostMapping
    public APIResponse create(@RequestBody PortfolioRequest request){
        return portfolioService.create(request);
    }

    @GetMapping("/{userId}")
    public APIResponse findAll(@PathVariable Long userId){
        return portfolioService.findAll(userId);
    }

}
