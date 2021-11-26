package com.monitoring.api.controllers;

import com.monitoring.api.entities.StockDataResponse;
import com.monitoring.api.services.interfaces.SymbolServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SymbolController {

    private final SymbolServiceIF challengeService;

    @Autowired
    public SymbolController(SymbolServiceIF challengeService) {
        this.challengeService = challengeService;
    }

    @RequestMapping(value = "/api/stockdata", method = RequestMethod.GET)
    public @ResponseBody
    StockDataResponse getStockData(@RequestParam("symbol") String symbol, @RequestParam("nDays") String nDays) {
//        ToDoo add logger statement
        return challengeService.calculateDataAndAverage(symbol, nDays);
    }
}
