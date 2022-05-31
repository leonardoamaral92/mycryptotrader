package com.lmarques.mystocktrader.services;

import com.lmarques.mystocktrader.model.Investor;
import com.lmarques.mystocktrader.model.dto.DepositRequest;
import com.lmarques.mystocktrader.repository.InvestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvestorService {
    @Autowired
    InvestorRepository investorRepository;
    public void deposit(DepositRequest depositRequest) {
        Investor investor = investorRepository.findByUserId(depositRequest.getUserId()).get();
        investor.setFunds(depositRequest.getDepositValue());
        investorRepository.save(investor);
    }
}
