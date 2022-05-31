package com.lmarques.mystocktrader.services;

import com.lmarques.mystocktrader.model.Investor;
import com.lmarques.mystocktrader.model.Operation;
import com.lmarques.mystocktrader.model.OperationType;
import com.lmarques.mystocktrader.model.Portfolio;
import com.lmarques.mystocktrader.model.dto.OperationResume;
import com.lmarques.mystocktrader.model.dto.PortfolioResume;
import com.lmarques.mystocktrader.repository.InvestorRepository;
import com.lmarques.mystocktrader.repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class PortfolioService {
    @Autowired
    InvestorRepository investorRepository;

    @Autowired
    PortfolioRepository portfolioRepository;

    @Autowired
    CryptocurrencyService cryptocurrencyService;


    @Transactional
    @Cacheable( cacheNames = "portfolioResume", key = "#root.method.name")
    public PortfolioResume generateResume(Long userId) {
        System.out.println("Construindo resumo do portfolio do usuario...");

        Investor investor = investorRepository.findByUserId(userId).get();

        List<Portfolio> portfolios = portfolioRepository.findByInvestorId(investor.getId());

        Set<Operation> operations = new HashSet<>();
        portfolios.forEach(portfolio -> operations.addAll(portfolio.getOperations()));

        HashMap<Long, List<Operation>> groupedOperations = operationsGroupByCoinId(operations);

        List<OperationResume> opResume = new ArrayList<>();
        groupedOperations.forEach((key, subList) -> opResume.add(generateResumeCoin(subList)) );

        Double sumAllOperations = opResume.stream().reduce(0.0, (subtotal, op) -> subtotal + op.getTotalValue(), Double::sum);
        Double totalBalance = opResume.stream().reduce(0.0, (subtotal, op) -> subtotal + op.getBalance(), Double::sum);
        Double profitsCash = totalBalance - sumAllOperations;

        PortfolioResume resume = PortfolioResume.builder()
                .totalBalance(totalBalance)
                .valueAllOperations(sumAllOperations)
                .profitsCash(profitsCash)
                .profitsPercent( (profitsCash / sumAllOperations) * 100 )
                .funds(investor.getFunds())
                .operationList(opResume)
                .build();

        return resume;
    }

    private OperationResume generateResumeCoin(List<Operation> operations){
        Double sumValue = operations.stream().reduce(0.0, (subtotal, operation) -> subtotal + operation.getValueByOperationType(), Double::sum);
        Double sumQtdCoin = operations.stream().reduce(0.0, (subtotal, operation) -> subtotal + operation.getQtdByOperationType(), Double::sum);
        Double balance = cryptocurrencyService.getCryptocurrencyPrice(operations.get(0).getCoinId()) * sumQtdCoin;
        Double profitsCash = balance - sumValue;

        return OperationResume.builder()
                .coinId(operations.get(0).getCoinId())
                .coinName(operations.get(0).getCoinName())
                .coinSymbol(operations.get(0).getCoinSymbol())
                .balance(balance)
                .qtdTotalCoin(sumQtdCoin)
                .totalValue(sumValue)
                .averagePrice(sumValue/sumQtdCoin)
                .profitsCash(profitsCash)
                .profitsPercent( (profitsCash / sumValue) * 100 )
                .build();
    }
    private HashMap<Long, List<Operation>> operationsGroupByCoinId( Set<Operation> operations ){

        HashMap<Long, List<Operation>> groupedOperations = new HashMap<>();

        operations.forEach(operation -> {
            if(!groupedOperations.containsKey(operation.getCoinId())){
                List<Operation> list = new ArrayList<>();
                list.add(operation);

                groupedOperations.put(operation.getCoinId(), list);
            }
            else
                groupedOperations.get(operation.getCoinId()).add(operation);
        });

        return groupedOperations;
    }
}
