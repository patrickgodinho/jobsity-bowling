package com.jobsity.patrick.validation;

import com.jobsity.patrick.model.Play;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BowlingValidations {

//    private boolean validatePinsNumber(Play play) {
//        List<int> pins = new ArrayList<>();
//        pins.add(play.getFirstScore());
//
//        if(play.getSecondScore() != null){
//            pins.add(play.getSecondScore());
//        }
//
//        if (play.getLastScore() != null) {
//            pins.add(play.getLastScore());
//        }
//
//        return pins.stream().allMatch(el -> {
//            if (el.equals("F")) return true;
//            int numberOfPins = Integer.valueOf(el);
//            return numberOfPins <= 10 && numberOfPins >= 0;
//        });
//    }
//
//    public boolean validatePinsNumberFromPlays(List<Play> plays) {
//        return plays.stream().allMatch(this::validatePinsNumber);
//    }
//
//    public boolean moreThanTenPlays(List<Play> plays) {
//        Map<String, Long> countForName = plays.stream().collect(Collectors.groupingBy(Play::getName, Collectors.counting()));
//        return countForName.entrySet().stream().noneMatch(el -> el.getValue() > 10);
//    }
}
