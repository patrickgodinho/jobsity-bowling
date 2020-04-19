package com.jobsity.patrick.service.impl;

import com.jobsity.patrick.model.Frame;
import com.jobsity.patrick.service.PrinterService;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class PrinterServiceImpl implements PrinterService {

    private String buildFrameString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Frame\t\t");
        IntStream.range(1, 11).forEach(e -> {
            stringBuilder.append(e);
            stringBuilder.append("\t\t");
        });
        return stringBuilder.toString();
    }

    private String buildPinFallsString(List<Frame> frames) {
        StringBuilder stringBuilder = new StringBuilder();

        frames.forEach(play -> {
            String firstScore = play.getFirstBallScore();
            String secondScore = play.getSecondBallScore();
            String finalScore = play.getFinalBallScore();

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
                if (play.getBall() == 10) {
                    stringBuilder.append("\t");
                    stringBuilder.append(finalScore);
                }
            }
        });
        return stringBuilder.toString();

    }

    private String buildScoreString(List<Frame> frames) {
        StringBuilder stringBuilder = new StringBuilder();
        frames.forEach(play -> {
            stringBuilder.append("\t\t");
            stringBuilder.append(play.getTotalScore());
        });
        return stringBuilder.toString();
    }

    @Override
    public String buildBoard(Map<String, List<Frame>> playsMap) {
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
        return stringBuilder.toString();
    }

    @Override
    public void printBoard(Map<String, List<Frame>> playsMap) {

        System.out.println(buildBoard(playsMap));
    }

}
