package com.jobsity.patrick.service;

import com.jobsity.patrick.model.Frame;

import java.util.List;
import java.util.Map;

public interface PrinterService {
    String buildBoard(Map<String, List<Frame>> playsMap);
    void printBoard(Map<String, List<Frame>> playsMap);
}
