package com.lmarques.mycryptotrader.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class APIResponse{

    private StatusResponse status;
    private String typeError;
    private String message;
    private Object data;

}
