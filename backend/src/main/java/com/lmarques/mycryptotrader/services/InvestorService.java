package com.lmarques.mycryptotrader.services;

import com.lmarques.mycryptotrader.model.Investor;
import com.lmarques.mycryptotrader.model.dto.APIResponse;
import com.lmarques.mycryptotrader.model.dto.DepositDTO;
import com.lmarques.mycryptotrader.model.dto.StatusResponse;
import com.lmarques.mycryptotrader.repository.InvestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .data(depositDTO)
                .build();
    }

    public APIResponse getFunds(Long userId) {
        Investor investor = investorRepository.findByUserId(userId).get();

        return APIResponse.builder()
                .status(StatusResponse.SUCCESS)
                .data(investor.getFunds())
                .build();
    }
}
