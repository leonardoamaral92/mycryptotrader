package com.lmarques.mycryptotrader.controllers;

import com.lmarques.mycryptotrader.cmc.model.CMCResponse;
import com.lmarques.mycryptotrader.services.CryptocurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cmc/cryptocurrencies")
public class CryptocurrencyController {
    @Autowired
    public CryptocurrencyService cryptocurrencyService;

    @GetMapping
    public CMCResponse getCmcCoinList(){
        return cryptocurrencyService.loadCmcList();
    }
}
