package com.lmarques.mycryptotrader.model.dto;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class TokenDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private Long investorId;
	private String accessToken;
	private String refreshToken;
	
	public TokenDTO() {}
	
	public TokenDTO(
			String username,
			String accessToken,
			String refreshToken) {
		this.username = username;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public void setInvestorId(Long investorId){
		this.investorId = investorId;
	}
}