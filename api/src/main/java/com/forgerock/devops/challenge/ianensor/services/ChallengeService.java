package com.forgerock.devops.challenge.ianensor.services;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.parameters.OutputSize;
import com.crazzyghost.alphavantage.timeseries.response.StockUnit;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;
import com.forgerock.devops.challenge.ianensor.config.ChallengeConfig;
import com.forgerock.devops.challenge.ianensor.entities.ChallengeResponse;
import com.forgerock.devops.challenge.ianensor.services.interfaces.ChallengeServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crazzyghost.alphavantage.Config;


import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Long.parseLong;

@Service
public class ChallengeService implements ChallengeServiceIF {

    private final ChallengeConfig challengeConfig;
    private long nDays;

    @Autowired
    public ChallengeService(ChallengeConfig challengeConfig) {
        this.challengeConfig = challengeConfig;
        Config config = Config.builder().key(challengeConfig.getApiKey()).timeOut(10).build();
        AlphaVantage.api().init(config);
        nDays = parseLong(challengeConfig.getNDays());
    }

    @Override
    public ChallengeResponse calculateDataAndAverage() {
        TimeSeriesResponse response = AlphaVantage.api()
                .timeSeries()
                .daily()
                .forSymbol(challengeConfig.getSymbol())
                .outputSize(OutputSize.FULL)
                .fetchSync();

        List<Double> lastNDays = response.getStockUnits().stream()
                .limit(nDays)
                .map(StockUnit::getClose)
                .collect(Collectors.toList());

        double average = lastNDays.stream().mapToDouble(d -> d).average().orElse(0.0);
        return new ChallengeResponse(challengeConfig.getSymbol(), lastNDays, average);
    }
}
