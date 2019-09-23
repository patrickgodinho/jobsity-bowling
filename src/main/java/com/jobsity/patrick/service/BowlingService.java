package com.jobsity.patrick.service;

import com.jobsity.patrick.model.Play;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public interface BowlingService {
    Map<String, List<Play>> calcPlayScoresFromMap(Map<String, List<Play>> playsMap);
    Map<String, List<Play>> buildPlayListFromStream(Stream<String> lines);
}
