package com.jobsity.patrick.validation.impl;

import com.jobsity.patrick.model.Play;
import com.jobsity.patrick.validation.BowlingValidations;

import java.util.ArrayList;
import java.util.List;

public class BowlingValidationsImpl implements BowlingValidations {

    private boolean validatePinsNumber(Play play) {
        List<Integer> pins = new ArrayList<>();
        pins.add(play.getFirstScore());
        pins.add(play.getSecondScore());
        pins.add(play.getFinalScore());

        return pins.stream().allMatch(el -> {
            if (el == 999) return true;
            int numberOfPins = el;
            return numberOfPins <= 10 && numberOfPins >= 0;
        });
    }

    private boolean validatePinsNumberFromPlays(List<Play> plays) {
        return plays.stream().allMatch(this::validatePinsNumber);
    }

    private boolean validateTenPlays(List<Play> plays) {
        return plays.size()<=10;
    }

    @Override
    public String validate(List<Play> plays){
        return
                !validateTenPlays(plays) ? "Bad input, more than 10 plays!" :
                !validatePinsNumberFromPlays(plays) ? "Bad input, invalid amount of pins!" :
                "";

    }
}
