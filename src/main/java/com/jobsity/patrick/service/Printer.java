package com.jobsity.patrick.service;

import com.jobsity.patrick.model.Play;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class Printer {

    private String buildFrameString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Frame\t\t");
        IntStream.range(1, 11).forEach(e -> {
            stringBuilder.append(e);
            stringBuilder.append("\t\t");
        });
        return stringBuilder.toString();
    }
    private String failWrapper(int test){
        return test == 999 ? "F" : String.valueOf(test);
    }

    private String buildPinFallsString(List<Play> plays) {
        StringBuilder stringBuilder = new StringBuilder();

        plays.forEach(play -> {
            String firstScore = this.failWrapper(play.getFirstScore());
            String secondScore = this.failWrapper(play.getSecondScore());
            String finalScore = this.failWrapper(play.getFinalScore());

            stringBuilder.append("\t");
            if (play.isStrike()) {
                stringBuilder.append("\tX");
            } else if (play.isSpare()) {
                stringBuilder.append(firstScore);
                stringBuilder.append("\t/");
            } else {
                stringBuilder.append(firstScore.equals("10") ? "X" : firstScore);
                stringBuilder.append("\t");
                stringBuilder.append(secondScore);
                if (play.getRound() == 10) {
                    stringBuilder.append("\t");
                    stringBuilder.append(finalScore);
                }
            }
        });
        return stringBuilder.toString();

    }

    private String buildScoreString(List<Play> plays) {
        StringBuilder stringBuilder = new StringBuilder();
        plays.forEach(play -> {
            stringBuilder.append("\t\t");
            stringBuilder.append(play.getTotalScore());
        });
        return stringBuilder.toString();
    }


    public void printBoard(Map<String, List<Play>> playsMap) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(buildFrameString());
        stringBuilder.append("\n");
        playsMap.forEach((name, plays) -> {
            stringBuilder.append(name);
            stringBuilder.append("\n");
            stringBuilder.append("Pinfalls");
            stringBuilder.append(buildPinFallsString(plays));
            stringBuilder.append("\n");
            stringBuilder.append("Score");
            stringBuilder.append(buildScoreString(plays));
            stringBuilder.append("\n");

        });
        System.out.println(stringBuilder.toString());
    }
}
