package com.forgerock.devops.challenge.ianensor.services;

import com.crazzyghost.alphavantage.AlphaVantage;
import com.crazzyghost.alphavantage.Config;
import com.crazzyghost.alphavantage.parameters.OutputSize;
import com.crazzyghost.alphavantage.timeseries.response.TimeSeriesResponse;
import com.forgerock.devops.challenge.ianensor.config.ChallengeConfig;
import com.forgerock.devops.challenge.ianensor.entities.ChallengeResponse;
import com.forgerock.devops.challenge.ianensor.testUtils.JsonUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;

import static com.forgerock.devops.challenge.ianensor.testUtils.JsonUtils.json;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ChallengeServiceTest {

    @Mock
    private ChallengeConfig challengeConfig;

    @Mock
    private Config config;

    @Mock
    private AlphaVantage alphaVantage;

    private ChallengeService challengeService;

    private TimeSeriesResponse timeSeriesResponse;

    private ChallengeResponse expected;
    private ChallengeResponse actual;

    @BeforeAll
    private void setUp() {
        JsonUtils.forDirectory("timeseries");
        MockitoAnnotations.openMocks(this);
        when(challengeConfig.getSymbol()).thenReturn("MSFT");
        when(challengeConfig.getApiKey()).thenReturn("An invalid API key");
        when(challengeConfig.getNDays()).thenReturn("7");
        challengeService = new ChallengeService(challengeConfig);
    }

    @Test
    public void testChallengeServiceReturnsExpectedResponse() throws IOException {
        givenTimeSeriesResponse();
        givenAlphaVantageApiIsMockedAndReturnsExpectedResponse();
        givenExpectedChallengeResponse();
        whenCalculateDataAndAverageIsCalled();
        thenExpectDataAndAveragesToBeCalculatedCorrectly();
    }

    private void givenExpectedChallengeResponse() {
        expected = new ChallengeResponse(challengeConfig.getSymbol(), new ArrayList<>(), 0.01);
    }

    private void givenTimeSeriesResponse() throws IOException {
        timeSeriesResponse = TimeSeriesResponse.of(json("dailyadjusted"), true);
    }

    private void givenAlphaVantageApiIsMockedAndReturnsExpectedResponse() {
        try (MockedStatic<AlphaVantage> alphaVantageMockedStatic = mockStatic(AlphaVantage.class)) {

            alphaVantageMockedStatic.when(() ->
                            AlphaVantage.api()
                                    .timeSeries()
                                    .daily()
                                    .forSymbol(anyString())
                                    .outputSize(any(OutputSize.class))
                                    .fetchSync()
            ).thenReturn(timeSeriesResponse);
        }
    }

    private void whenCalculateDataAndAverageIsCalled() {
        actual = challengeService.calculateDataAndAverage();
    }

    private void thenExpectDataAndAveragesToBeCalculatedCorrectly() {
        assertEquals(expected, actual);
    }
}
