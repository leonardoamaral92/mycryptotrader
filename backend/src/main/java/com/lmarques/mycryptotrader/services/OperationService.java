package com.lmarques.mycryptotrader.services;

import com.lmarques.mycryptotrader.model.Operation;
import com.lmarques.mycryptotrader.model.OperationType;
import com.lmarques.mycryptotrader.model.Portfolio;
import com.lmarques.mycryptotrader.model.dto.APIResponse;
import com.lmarques.mycryptotrader.model.dto.Order;
import com.lmarques.mycryptotrader.model.dto.StatusResponse;
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

    @CacheEvict(cacheNames = "portfolioResume", allEntries = true)
    public APIResponse createOperation(Order order, OperationType typeOp) {
        Portfolio portfolio = portfolioRepository.findById(order.getPortfolioId()).get();
        Operation operation = new Operation(order, portfolio, typeOp);

        Operation operationSaved = operationRepository.save(operation);
        Order responseOrder = Order.builder()
                .coinId(operationSaved.getCoinId())
                .coinName(operationSaved.getCoinName())
                .coinPrice(operationSaved.getCoinPrice())
                .coinSymbol(operationSaved.getCoinSymbol())
                .date(operationSaved.getDate())
                .portfolioId(operationSaved.getPortfolio().getId())
                .qtdCoin(operationSaved.getQtdCoin())
                .totalValue(operationSaved.getTotalValue())
                .type(operationSaved.getType())
                .build();

        return APIResponse.builder()
                .status(StatusResponse.SUCCESS)
                .data(responseOrder)
                .build();
    }
}
