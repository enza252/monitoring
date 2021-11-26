package com.monitoring.api.controllers;

import com.monitoring.api.entities.StockDataResponse;
import com.monitoring.api.services.interfaces.SymbolServiceIF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static java.lang.String.format;
import static java.util.Objects.isNull;

@Controller
public class SymbolController {

    private final SymbolServiceIF challengeService;
    private static final Logger LOGGER = LoggerFactory.getLogger(SymbolController.class);

    @Autowired
    public SymbolController(SymbolServiceIF challengeService) {
        this.challengeService = challengeService;
    }

    @RequestMapping(value = "/api/stockdata", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<StockDataResponse> getStockData(@RequestParam("symbol") String symbol, @RequestParam("nDays") String nDays) {
        LOGGER.debug(format("Received request to fetch stock data for Symbol %s for the last nDays %s", symbol, nDays));
        StockDataResponse stockDataResponse = challengeService.calculateDataAndAverage(symbol, nDays);
        if (isNull(stockDataResponse)) {
            return ResponseEntity.ok(null);
        }
        LOGGER.debug(format("Successfully fetched stock data for symbol %s for the last nDays %s", symbol, nDays));
        return ResponseEntity.ok(stockDataResponse);
    }
}
