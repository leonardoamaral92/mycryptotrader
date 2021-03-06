package com.lmarques.mycryptotrader.services;

import com.lmarques.mycryptotrader.model.Investor;
import com.lmarques.mycryptotrader.model.Operation;
import com.lmarques.mycryptotrader.model.Portfolio;
import com.lmarques.mycryptotrader.model.dto.*;
import com.lmarques.mycryptotrader.repository.InvestorRepository;
import com.lmarques.mycryptotrader.repository.PortfolioRepository;
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
    public APIResponse create(PortfolioDTO request) {
        Investor investor = investorRepository.findById(request.getInvestorId()).get();
        Portfolio portfolioSaved = portfolioRepository.save(new Portfolio(request.getName(), investor));
        PortfolioDTO portfolioDTO = PortfolioDTO.builder()
                .id(portfolioSaved.getId())
                .name(portfolioSaved.getName())
                .build();

        return APIResponse.builder()
                .status(StatusResponse.SUCCESS)
                .data(portfolioDTO)
                .build();
    }

    @Transactional
    public APIResponse findAll(Long investorId) {
        Investor investor = investorRepository.findById(investorId).get();
        Set<Portfolio> portfolios = investor.getPortfolioSet();
        List<PortfolioDTO> portfoliosDTO = new ArrayList<>();

        portfolios.forEach(portfolio -> portfoliosDTO.add(PortfolioDTO.builder()
                .id(portfolio.getId())
                .name(portfolio.getName())
                .build()) );

        return APIResponse.builder()
                .status(StatusResponse.SUCCESS)
                .data(portfoliosDTO)
                .build();
    }

    public APIResponse alterName(PortfolioDTO request) {

        Portfolio portfolio = portfolioRepository.findById(request.getId()).get();
        portfolio.setName(request.getName());

        Portfolio altearedPortfolio = portfolioRepository.save(portfolio);
        PortfolioDTO portfolioDTO = PortfolioDTO.builder()
                .id(altearedPortfolio.getId())
                .name(portfolio.getName())
                .build();

        return APIResponse.builder()
                .status(StatusResponse.SUCCESS)
                .data(portfolioDTO)
                .build();
    }
    public void delete(Long id){
        Portfolio portfolio = portfolioRepository.findById(id).get();
        portfolioRepository.delete(portfolio);
    }

    @Transactional
    @Cacheable( cacheNames = "portfolioResume", key = "#root.method.name")
    public APIResponse generateResume(Long investorId) {
        System.out.println("Construindo resumo do portfolio do usuario...");

        Investor investor = investorRepository.findById(investorId).get();

        List<Portfolio> portfolios = portfolioRepository.findByInvestorId(investor.getId());

        Set<Operation> operations = new HashSet<>();
        portfolios.forEach(portfolio -> operations.addAll(portfolio.getOperations()));

        HashMap<Long, List<Operation>> groupedOperations = operationsGroupByCoinId(operations);

        List<OperationResume> opResume = new ArrayList<>();
        groupedOperations.forEach((key, subList) -> opResume.add(generateResumeCoin(subList)) );

        Double sumAllOperations = opResume.stream().reduce(0.0, (subtotal, op) -> subtotal + op.getTotalValue(), Double::sum);
        Double totalBalance = opResume.stream().reduce(0.0, (subtotal, op) -> subtotal + op.getBalance(), Double::sum);
        Double profitsCash = totalBalance - sumAllOperations;
        Double profitsPercent = profitsCash != 0.0 ? (profitsCash / sumAllOperations) * 100 : 0.0;

        PortfolioResume resume = PortfolioResume.builder()
                .totalBalance(totalBalance)
                .valueAllOperations(sumAllOperations)
                .profitsCash(profitsCash)
                .profitsPercent(profitsPercent)
                .funds(investor.getFunds())
                .operationList(opResume)
                .build();

        return APIResponse.builder()
                .status(StatusResponse.SUCCESS)
                .data(resume)
                .build();
    }

    private OperationResume generateResumeCoin(List<Operation> operations){
        Double sumValue = operations.stream().reduce(0.0, (subtotal, operation) -> subtotal + operation.getValueByOperationType(), Double::sum);
        Double sumQtdCoin = operations.stream().reduce(0.0, (subtotal, operation) -> subtotal + operation.getQtdByOperationType(), Double::sum);
        Double currentPrice = cryptocurrencyService.getCryptocurrencyPrice(operations.get(0).getCoinId());
        Double balance = currentPrice * sumQtdCoin;
        Double profitsCash = balance - sumValue;
        Double profitsPercent = (profitsCash / sumValue) * 100;

        return OperationResume.builder()
                .coinId(operations.get(0).getCoinId())
                .coinName(operations.get(0).getCoinName())
                .coinSymbol(operations.get(0).getCoinSymbol())
                .balance(balance)
                .qtdTotalCoin(sumQtdCoin)
                .totalValue(sumValue)
                .currentPrice(currentPrice)
                .averagePrice(sumValue/sumQtdCoin)
                .profitsCash(profitsCash)
                .profitsPercent(profitsPercent)
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
