package com.lmarques.mystocktrader.services;

import com.lmarques.mystocktrader.model.Operation;
import com.lmarques.mystocktrader.model.OperationType;
import com.lmarques.mystocktrader.model.Portfolio;
import com.lmarques.mystocktrader.model.dto.APIResponse;
import com.lmarques.mystocktrader.model.dto.Order;
import com.lmarques.mystocktrader.model.dto.OrderStatus;
import com.lmarques.mystocktrader.model.dto.StatusResponse;
import com.lmarques.mystocktrader.repository.OperationRepository;
import com.lmarques.mystocktrader.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.Collections;

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
                .data(Collections.singletonList(responseOrder))
                .build();
    }
}
