package com.lmarques.mystocktrader.controllers;

import com.lmarques.mystocktrader.model.dto.APIResponse;
import com.lmarques.mystocktrader.model.dto.DepositDTO;
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
    public APIResponse deposit(@RequestBody DepositDTO depositRequest){
        return investorService.deposit(depositRequest);
    }
}
