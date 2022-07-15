package com.lmarques.mycryptotrader.services;

import com.lmarques.mycryptotrader.exception.InvalidOperationException;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationService {
    @Autowired
    PortfolioRepository portfolioRepository;

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    InvestorRepository investorRepository;

    @CacheEvict(cacheNames = "portfolioResume", allEntries = true)
    @Transactional
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

    @CacheEvict(cacheNames = "portfolioResume", allEntries = true)
    @Transactional
    public APIResponse sellCrypto(Order order) {
        Investor investor = investorRepository.findById(order.getInvestorId()).get();
        Portfolio portfolio = portfolioRepository.findById(order.getPortfolioId()).get();

        try{
            validateOperation(order, portfolio);
        }
        catch (Exception e){
            return APIResponse.builder()
                    .status(StatusResponse.FAILED)
                    .message(e.getMessage())
                    .data(order)
                    .build();
        }

        Operation operation = new Operation(order, portfolio, OperationType.SELL);
        operationRepository.save(operation);

        investor.setFunds(investor.getFunds() + order.getTotalValue());
        investorRepository.save(investor);

        return APIResponse.builder()
                .status(StatusResponse.SUCCESS)
                .build();
    }

    public void validateOperation(Order order, Portfolio portfolio) throws InvalidOperationException {
        if(order == null || order.getPortfolioId() == null || order.getInvestorId() == null || order.getQtdCoin() == 0){
            throw new InvalidOperationException("Entrada inválida. Verifique os campos preenchidos.");
        }

        portfolio
            .getOperations()
            .stream()
            .forEach( op -> System.out.println("opCoinId: " + op.getCoinId()));

        List<Operation> operationsByCoin = portfolio
                .getOperations()
                .stream()
                .filter(operation -> order.getCoinId().compareTo(operation.getCoinId()) == 0)
                .collect(Collectors.toList());

        if(operationsByCoin.isEmpty()){
            throw new InvalidOperationException("Verifique se possui saldo na moeda selecionada.");
        }

        if(sumTotalCoin(operationsByCoin) < order.getQtdCoin()){
            throw new InvalidOperationException("Quantidade maior do que possui: ${filteredCoin.qtdTotalCoin}`)");
        }
    }

    public Double sumTotalCoin(List<Operation> operationsByCoin){
        return operationsByCoin.stream().reduce(0.0, (sum, op) -> sum + op.getQtdCoin(), Double::sum );
    }
}
