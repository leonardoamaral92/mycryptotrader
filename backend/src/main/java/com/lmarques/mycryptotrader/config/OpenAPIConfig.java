package com.lmarques.mycryptotrader.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MyCryptoTrader")
                        .version("v1")
                        .description("Gerenciador de portfólio de criptomoedas")
                        .termsOfService("")
                        .license(new License().name("Apache 2.0")))
                ;
    }
}
