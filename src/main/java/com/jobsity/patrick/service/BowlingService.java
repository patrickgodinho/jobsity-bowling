package com.jobsity.patrick.service;

import com.jobsity.patrick.model.Play;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BowlingService {


    private Map<String, List<Play>> buildPlayesByPlayer(Map<String, List<String>> playsMap) {
        Map<String, List<Play>> plays = new HashMap<>();
        playsMap.forEach((name, values) -> {
            List<Play> playList = new ArrayList<>();
            AtomicReference<Play> currentPlay = new AtomicReference<>();
            AtomicInteger count = new AtomicInteger(0);
            AtomicInteger round = new AtomicInteger(1);
            values.forEach(v -> {
                int score = Integer.valueOf(v.split(" ")[1].replace("F", "999"));
                if (count.intValue() == 0) currentPlay.set(new Play());
                if ((!currentPlay.get().isOpen()) || count.intValue() == 0) {
                    currentPlay.get().setFirstScore(score);
                    if (score == 10 && count.intValue() < values.size() - 3) {
                        currentPlay.get().setRound(round.intValue());
                        round.incrementAndGet();
                        playList.add(currentPlay.get());
                        currentPlay.set(new Play());
                    } else {
                        currentPlay.get().setOpen(true);
                    }
                } else if (count.intValue() == values.size() - 1) {
                    currentPlay.get().setFinalScore(score);
                    currentPlay.get().setRound(round.intValue());
                    round.incrementAndGet();
                    playList.add(currentPlay.get());
                } else {
                    currentPlay.get().setSecondScore(score);
                    if (count.intValue() < values.size() - 2) {
                        currentPlay.get().setRound(round.intValue());
                        round.incrementAndGet();
                        playList.add(currentPlay.get());
                        currentPlay.set(new Play());
                    }
                }
                count.incrementAndGet();
            });
            plays.put(name, playList);
        });
        return plays;
    }

    private int failWrapper(int test){
        return test == 999 ? 0 : test;
    }

    private int calcStrikeScore(int round, List<Play> plays) {
        int total = 10;
        int index = round - 1;
        int previousPlayScore = index != 0 ? plays.get(index - 1).getTotalScore() : 0;
        if (round < plays.size()) {
            Play nextPlay = plays.get(index + 1);
            if (nextPlay.isStrike()) {
                Play nextNextPlay = plays.get(index + 2);
                total += previousPlayScore + failWrapper(nextPlay.getFirstScore()) + failWrapper(nextNextPlay.getFirstScore());
            } else {
                total += previousPlayScore + failWrapper(nextPlay.getFirstScore()) + failWrapper(nextPlay.getSecondScore());
            }
        }
        return total;
    }

    private int calcSpareScore(int round, List<Play> plays) {
        int total = 10;
        if (round < plays.size()) {
            int index = round - 1;
            int previousPlayScore = index != 0 ? plays.get(index - 1).getTotalScore() : 0;
            Play nextPlay = plays.get(index + 1);
            total += previousPlayScore + failWrapper(nextPlay.getFirstScore());
        }
        return total;
    }

    private int calcNormalScore(int round, List<Play> plays) {
        int index = round - 1;
        int previousPlayScore = index != 0 ? plays.get(index - 1).getTotalScore() : 0;
        Play play = plays.get(index);
        return failWrapper(play.getFirstScore()) + failWrapper(play.getSecondScore()) + failWrapper(play.getFinalScore()) +  previousPlayScore;
    }

    private List<Play> calcPlayScoresFromList(List<Play> playes) {
        playes.forEach((play) -> {
            AtomicInteger total = new AtomicInteger();
            if (play.isStrike()) {
                total.set(calcStrikeScore(play.getRound(), playes));
            } else if (play.isSpare()) {
                total.set(calcSpareScore(play.getRound(), playes));
            } else {
                total.set(calcNormalScore(play.getRound(), playes));
            }
            play.setTotalScore(total.intValue());
        });


        return playes;
    }

    public Map<String, List<Play>> calcPlayScoresFromMap(Map<String, List<Play>> playesMap) {

        playesMap.forEach((name, values) -> {
            List<Play> plays = calcPlayScoresFromList(values);
            playesMap.put(name, plays);
        });

        return playesMap;
    }


    public Map<String, List<Play>> buildPlayListFromStream(Stream<String> lines) {

        LinkedHashMap<String, List<String>> playsMap = lines.collect(
                Collectors.groupingBy(s -> s.substring(0, s.indexOf(" ")), LinkedHashMap::new, Collectors.mapping(p -> p, //map name
                        Collectors.toList())));

        return this.buildPlayesByPlayer(playsMap);

    }

}
