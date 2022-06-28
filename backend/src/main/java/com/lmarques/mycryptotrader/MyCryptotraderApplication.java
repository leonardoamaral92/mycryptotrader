package com.lmarques.mycryptotrader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MyCryptotraderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyCryptotraderApplication.class, args);
	}

}
