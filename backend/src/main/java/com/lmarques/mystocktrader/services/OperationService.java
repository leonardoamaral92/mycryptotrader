package com.lmarques.mystocktrader.services;

import com.lmarques.mystocktrader.model.Operation;
import com.lmarques.mystocktrader.model.OperationType;
import com.lmarques.mystocktrader.model.Portfolio;
import com.lmarques.mystocktrader.model.dto.Order;
import com.lmarques.mystocktrader.repository.OperationRepository;
import com.lmarques.mystocktrader.repository.PortfolioRepository;
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
    public void createOperation(Order order, OperationType typeOp) {
        Portfolio portfolio = portfolioRepository.findById(order.getPortfolioId()).get();
        Operation operation = new Operation(order, portfolio, typeOp);

        operationRepository.save(operation);
    }
}
