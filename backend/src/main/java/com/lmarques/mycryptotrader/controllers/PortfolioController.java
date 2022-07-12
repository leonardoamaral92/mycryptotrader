package com.lmarques.mycryptotrader.controllers;

import com.lmarques.mycryptotrader.model.dto.APIResponse;
import com.lmarques.mycryptotrader.model.dto.PortfolioDTO;
import com.lmarques.mycryptotrader.services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/portfolios")
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    @GetMapping("/{investorId}/resume")
    public APIResponse resumePortfolio(@PathVariable Long investorId){
        return portfolioService.generateResume(investorId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public APIResponse create(@RequestBody PortfolioDTO request){
        return portfolioService.create(request);
    }

    @GetMapping("/{investorId}")
    public APIResponse findAll(@PathVariable Long investorId){
        return portfolioService.findAll(investorId);
    }

    @PutMapping
    public APIResponse alterName(@RequestBody PortfolioDTO request){
        return portfolioService.alterName(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        portfolioService.delete(id);
    }

}
