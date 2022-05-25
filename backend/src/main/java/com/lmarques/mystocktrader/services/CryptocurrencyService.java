package com.lmarques.mystocktrader.services;

import com.lmarques.mystocktrader.cmc.HttpClientTemplate;
import com.lmarques.mystocktrader.cmc.model.CMCResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CryptocurrencyService {
    @Autowired
    HttpClientTemplate httpClientTemplate;

    @Value("${cmc.list.endpoint}")
    private String cmcListEndpoint;
    public CMCResponse loadCmcList() {
        try {
            return httpClientTemplate.request(cmcListEndpoint);
        } catch (IOException e) {
            //TODO Melhorar esse tratamento de erro
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
