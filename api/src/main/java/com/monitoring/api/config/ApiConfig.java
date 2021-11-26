package com.monitoring.api.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ApiConfig {
    @Value("${request.symbol}")
    private String symbol;
    @Value("${request.ndays}")
    private String nDays;
    @Value("${request.api.key}")
    private String apiKey;
}
