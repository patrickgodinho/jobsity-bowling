package com.jobsity.patrick.service;

import com.jobsity.patrick.model.Play;

import java.util.List;
import java.util.Map;

public interface PrinterService {
    String buildBoard(Map<String, List<Play>> playsMap);
    void printBoard(Map<String, List<Play>> playsMap);
}
