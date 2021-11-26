package com.monitoring.api.services.interfaces;

import com.monitoring.api.entities.StockDataResponse;

public interface SymbolServiceIF {
    StockDataResponse calculateDataAndAverage(String symbol, String nDays);
}
