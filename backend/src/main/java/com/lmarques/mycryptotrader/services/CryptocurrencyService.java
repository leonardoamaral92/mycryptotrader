package com.lmarques.mycryptotrader.services;

import com.lmarques.mycryptotrader.cmc.HttpClientTemplate;
import com.lmarques.mycryptotrader.cmc.model.CMCCoin;
import com.lmarques.mycryptotrader.cmc.model.CMCResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CryptocurrencyService {
    @Autowired
    HttpClientTemplate httpClientTemplate;

    @Value("${cmc.list.endpoint}")
    private String cmcListEndpoint;

    public CMCResponse loadCmcList() {
        try {
            System.out.println("Buscando lista de moedas na CMC...");
            return httpClientTemplate.request(cmcListEndpoint, Optional.empty());
        } catch (IOException e) {
            //TODO Melhorar esse tratamento de erro
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Double getCryptocurrencyPrice(Long coinId){
        CMCResponse response = loadCmcList();

        List<CMCCoin> coin = response.getData()
                .stream()
                .filter(cmcCoin -> Objects.equals(cmcCoin.getId(), coinId))
                .collect(Collectors.toList());

        System.out.println(coin.get(0).getName() + " " + coin.get(0).getId());

        return !coin.isEmpty() ? coin.get(0).getQuote().getUsd().getPrice() : 0.0;
    }
}
