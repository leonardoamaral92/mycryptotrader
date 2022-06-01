package com.lmarques.mystocktrader.services;

import com.lmarques.mystocktrader.model.Investor;
import com.lmarques.mystocktrader.model.dto.APIResponse;
import com.lmarques.mystocktrader.model.dto.DepositDTO;
import com.lmarques.mystocktrader.model.dto.StatusResponse;
import com.lmarques.mystocktrader.repository.InvestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class InvestorService {
    @Autowired
    InvestorRepository investorRepository;
    public APIResponse deposit(DepositDTO depositRequest) {
        Investor investor = investorRepository.findByUserId(depositRequest.getUserId()).get();
        investor.setFunds(depositRequest.getDepositValue()+investor.getFunds());
        Investor investorSaved = investorRepository.save(investor);
        DepositDTO depositDTO = DepositDTO.builder()
                .userId(investorSaved.getUser().getId())
                .depositValue(depositRequest.getDepositValue())
                .newFunds(investorSaved.getFunds())
                .build();

        return APIResponse.builder()
                .status(StatusResponse.SUCCESS)
                .data(Collections.singletonList(depositDTO))
                .build();
    }
}
