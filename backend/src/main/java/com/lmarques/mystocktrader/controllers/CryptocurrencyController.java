package com.lmarques.mystocktrader.controllers;

import com.lmarques.mystocktrader.cmc.model.CMCResponse;
import com.lmarques.mystocktrader.services.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/cryptocurrencies")
public class CryptocurrencyController {
    @Autowired
    public CryptocurrencyService cryptocurrencyService;

    @GetMapping()
    public CMCResponse getCmcCoinList(){
        return cryptocurrencyService.loadCmcList();
    }

}
