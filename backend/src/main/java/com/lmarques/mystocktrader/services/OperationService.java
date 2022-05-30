package com.lmarques.mystocktrader.services;

import com.lmarques.mystocktrader.model.Operation;
import com.lmarques.mystocktrader.model.OperationType;
import com.lmarques.mystocktrader.model.Portfolio;
import com.lmarques.mystocktrader.model.dto.Order;
import com.lmarques.mystocktrader.repository.OperationRepository;
import com.lmarques.mystocktrader.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationService {
    @Autowired
    PortfolioRepository portfolioRepository;

    @Autowired
    OperationRepository operationRepository;

    public void buyCrypto(Order order) {

        Portfolio portfolio = portfolioRepository.findById(order.getPortfolioId()).get();

        Operation operation = Operation.builder()
                .coinId(order.getCoinId())
                .coinName(order.getCoinName())
                .coinPrice(order.getCoinPrice())
                .coinSymbol(order.getCoinSymbol())
                .date(order.getDate())
                .qtdCoin(order.getQtdCoin())
                .portfolio(portfolio)
                .totalValue(order.getTotalValue())
                .type(OperationType.BUY)
                .build();

        operationRepository.save(operation);
    }
}
