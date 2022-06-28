package com.lmarques.mycryptotrader.controllers;

import com.lmarques.mycryptotrader.model.OperationType;
import com.lmarques.mycryptotrader.model.dto.APIResponse;
import com.lmarques.mycryptotrader.model.dto.Order;
import com.lmarques.mycryptotrader.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/operations")
public class OperationController {

    @Autowired
    OperationService operationService;

    @PostMapping("/buy")
    public APIResponse buyCryptocurrency(@RequestBody Order order){
        return operationService.createOperation(order, OperationType.BUY);
    }

    @PostMapping("/sell")
    public APIResponse sellCryptocurrency(@RequestBody Order order){
        return operationService.createOperation(order, OperationType.SELL);
    }
}
