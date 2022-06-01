package com.lmarques.mystocktrader.controllers;

import com.lmarques.mystocktrader.model.OperationType;
import com.lmarques.mystocktrader.model.dto.APIResponse;
import com.lmarques.mystocktrader.model.dto.Order;
import com.lmarques.mystocktrader.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
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
