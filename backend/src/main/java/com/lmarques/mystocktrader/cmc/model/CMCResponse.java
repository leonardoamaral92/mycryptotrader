package com.lmarques.mystocktrader.cmc.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
public class CMCResponse implements Serializable {
    CMCStatus status;
    List<CMCCoin> data;
}
