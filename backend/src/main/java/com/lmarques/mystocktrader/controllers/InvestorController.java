package com.lmarques.mystocktrader.controllers;

import com.lmarques.mystocktrader.model.dto.DepositRequest;
import com.lmarques.mystocktrader.services.InvestorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/investor")
public class InvestorController {

    @Autowired
    InvestorService investorService;

    @PostMapping("/deposit")
    public void deposit(@RequestBody DepositRequest depositRequest){
        investorService.deposit(depositRequest);
    }
}
