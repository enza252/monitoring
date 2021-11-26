package com.monitoring.api.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class StockDataResponse {
    private String symbol;
    private List<Double> data;
    private Double average;
}
