package com.lmarques.mycryptotrader.controllers;

import com.lmarques.mycryptotrader.model.dto.APIResponse;
import com.lmarques.mycryptotrader.model.dto.PortfolioDTO;
import com.lmarques.mycryptotrader.services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("portfolios")
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;

    @GetMapping("/{userId}/resume")
    public APIResponse resumePortfolio(@PathVariable Long userId){
        return portfolioService.generateResume(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public APIResponse create(@RequestBody PortfolioDTO request){
        return portfolioService.create(request);
    }

    @GetMapping("/{userId}")
    public APIResponse findAll(@PathVariable Long userId){
        return portfolioService.findAll(userId);
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
