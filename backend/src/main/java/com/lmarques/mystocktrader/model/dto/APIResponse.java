package com.lmarques.mystocktrader.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class APIResponse{

    private StatusResponse status;
    private String typeError;
    private String message;
    private Object data;

}
