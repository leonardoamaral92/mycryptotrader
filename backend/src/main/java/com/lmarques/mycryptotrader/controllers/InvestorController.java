package com.lmarques.mycryptotrader.controllers;

import com.lmarques.mycryptotrader.model.dto.APIResponse;
import com.lmarques.mycryptotrader.model.dto.DepositDTO;
import com.lmarques.mycryptotrader.model.dto.InvestorDTO;
import com.lmarques.mycryptotrader.services.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("investor")
public class InvestorController {

    @Autowired
    InvestorService investorService;

    @PostMapping("/deposit")
    public APIResponse deposit(@RequestBody DepositDTO depositRequest){
        return investorService.deposit(depositRequest);
    }

    @GetMapping("/funds/{userId}")
    public APIResponse getFunds(@PathVariable Long userId){
        return investorService.getFunds(userId);
    }

    @PostMapping
    public APIResponse create(@RequestBody InvestorDTO investorDTO){
        return investorService.create(investorDTO);
    }
}
