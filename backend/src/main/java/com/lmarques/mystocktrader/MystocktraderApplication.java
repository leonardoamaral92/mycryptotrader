package com.lmarques.mystocktrader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableCaching
public class MystocktraderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MystocktraderApplication.class, args);
	}

}
