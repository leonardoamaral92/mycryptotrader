package com.lmarques.mystocktrader.cmc.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CMCResponse {
    CMCStatus status;
    List<CMCCoin> data;
}
