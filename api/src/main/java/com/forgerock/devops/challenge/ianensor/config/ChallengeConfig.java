package com.forgerock.devops.challenge.ianensor.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ChallengeConfig {
    @Value("${forgerock.challenge.symbol}")
    private String symbol;
    @Value("${forgerock.challenge.ndays}")
    private String nDays;
    @Value("${forgerock.challenge.api.key}")
    private String apiKey;
}
