package com.monitoring.api.services;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.parameters.OutputSize;
import com.crazzyghost.alphavantage.timeseries.response.StockUnit;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;
import com.monitoring.api.config.ApiConfig;
import com.monitoring.api.entities.StockDataResponse;
import com.monitoring.api.services.interfaces.SymbolServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crazzyghost.alphavantage.Config;


import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Long.parseLong;

@Service
public class SymbolService implements SymbolServiceIF {

    private final ApiConfig apiConfig;

    @Autowired
    public SymbolService(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
        Config config = Config.builder().key(apiConfig.getApiKey()).timeOut(10).build();
        AlphaVantage.api().init(config);
    }

    @Override
    public StockDataResponse calculateDataAndAverage(String symbol, String nDays) {
        long nDaysLong = parseLong(nDays);
        TimeSeriesResponse response = AlphaVantage.api()
                .timeSeries()
                .daily()
                .forSymbol(symbol)
                .outputSize(OutputSize.FULL)
                .fetchSync();

        List<Double> lastNDays = response.getStockUnits().stream()
                .limit(nDaysLong)
                .map(StockUnit::getClose)
                .collect(Collectors.toList());

        double average = lastNDays.stream().mapToDouble(d -> d).average().orElse(0.0);
        return new StockDataResponse(symbol, lastNDays, average);
    }
}
