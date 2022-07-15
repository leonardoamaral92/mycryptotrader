package com.lmarques.mycryptotrader.services;

import com.lmarques.mycryptotrader.model.Investor;
import com.lmarques.mycryptotrader.model.Operation;
import com.lmarques.mycryptotrader.model.OperationType;
import com.lmarques.mycryptotrader.model.Portfolio;
import com.lmarques.mycryptotrader.model.dto.APIResponse;
import com.lmarques.mycryptotrader.model.dto.Order;
import com.lmarques.mycryptotrader.model.dto.StatusResponse;
import com.lmarques.mycryptotrader.repository.InvestorRepository;
import com.lmarques.mycryptotrader.repository.OperationRepository;
import com.lmarques.mycryptotrader.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class OperationService {
    @Autowired
    PortfolioRepository portfolioRepository;

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    InvestorRepository investorRepository;

    @CacheEvict(cacheNames = "portfolioResume", allEntries = true)
    public APIResponse buyCrypto(Order order) {
        Investor investor = investorRepository.findById(order.getInvestorId()).get();

        if(order.getTotalValue() > investor.getFunds())
            return APIResponse.builder()
                    .status(StatusResponse.FAILED)
                    .message("Investidor não possui saldo suficiente para a operação.")
                    .data(order)
                    .build();

        Portfolio portfolio = portfolioRepository.findById(order.getPortfolioId()).get();
        Operation operation = new Operation(order, portfolio, OperationType.BUY);
        operationRepository.save(operation);

        investor.setFunds(investor.getFunds() - order.getTotalValue());
        investorRepository.save(investor);

        return APIResponse.builder()
                .status(StatusResponse.SUCCESS)
                .build();
    }

    public APIResponse sellCrypto(Order order) {
        return APIResponse.builder()
                .status(StatusResponse.SUCCESS)
                .data(order)
                .build();
    }
}
