package com.monitoring.api.services;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.parameters.OutputSize;
import com.crazzyghost.alphavantage.timeseries.response.StockUnit;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;
import com.monitoring.api.config.ApiConfig;
import com.monitoring.api.entities.StockDataResponse;
import com.monitoring.api.services.interfaces.SymbolServiceIF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crazzyghost.alphavantage.Config;


import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Long.parseLong;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class SymbolService implements SymbolServiceIF {

    private final ApiConfig apiConfig;
    private static final Logger LOGGER = LoggerFactory.getLogger(SymbolService.class);

    @Autowired
    public SymbolService(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
        Config config = Config.builder().key(apiConfig.getApiKey()).timeOut(10).build();
        AlphaVantage.api().init(config);
    }

    @Override
    public StockDataResponse calculateDataAndAverage(String symbol, String nDays) {
        TimeSeriesResponse response = AlphaVantage.api()
                .timeSeries()
                .daily()
                .forSymbol(symbol)
                .outputSize(OutputSize.FULL)
                .fetchSync();

        if (nonNull(response.getErrorMessage())) {
            LOGGER.error(format("Failed to obtain stock data for Symbol %s and nDays %s with error %s",
                    symbol,
                    nDays,
                    response.getErrorMessage()));
            return null;
        }

        List<Double> lastNDays = response.getStockUnits().stream()
                .limit(parseLong(nDays))
                .map(StockUnit::getClose)
                .collect(Collectors.toList());

        double average = lastNDays.stream().mapToDouble(d -> d).average().orElse(0.0);
        return new StockDataResponse(symbol, lastNDays, average);
    }
}
