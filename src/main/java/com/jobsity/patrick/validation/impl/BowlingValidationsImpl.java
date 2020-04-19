package com.jobsity.patrick.validation.impl;

import com.jobsity.patrick.model.Frame;
import com.jobsity.patrick.utils.Helper;
import com.jobsity.patrick.validation.BowlingValidations;

import java.util.ArrayList;
import java.util.List;

public class BowlingValidationsImpl implements BowlingValidations {

    private boolean validatePinsNumber(Frame frame) {
        List<String> pins = new ArrayList<>();
        pins.add(frame.getFirstBallScore());
        pins.add(frame.getSecondBallScore());
        pins.add(frame.getFinalBallScore());

        return pins.stream().allMatch(el -> {
            int numberOfPins = Helper.getIntegerValue(el);
            return numberOfPins <= 10 && numberOfPins >= 0;
        });
    }

    private boolean validatePinsNumberFromPlays(List<Frame> frames) {
        return frames.stream().allMatch(this::validatePinsNumber);
    }

    private boolean validateTenPlays(List<Frame> frames) {
        return frames.size() <= 10;
    }

    @Override
    public String validate(List<Frame> frames) {
        return
                !validateTenPlays(frames) ? "Bad input, more than 10 plays!" :
                        !validatePinsNumberFromPlays(frames) ? "Bad input, invalid amount of pins!" :
                                "";

    }
}
