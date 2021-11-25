package com.forgerock.devops.challenge.ianensor.controllers;

import com.forgerock.devops.challenge.ianensor.entities.ChallengeResponse;
import com.forgerock.devops.challenge.ianensor.services.interfaces.ChallengeServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChallengeController {

    private final ChallengeServiceIF challengeService;

    @Autowired
    public ChallengeController(ChallengeServiceIF challengeService) {
        this.challengeService = challengeService;
    }

    @RequestMapping(value = "/api/challenge", method = RequestMethod.GET)
    public @ResponseBody ChallengeResponse getStockData() {
        return challengeService.calculateDataAndAverage();
    }
}
