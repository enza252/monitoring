package com.forgerock.devops.challenge.ianensor.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class ChallengeResponse {
    private String symbol;
    private List<Double> data;
    private Double average;
}
