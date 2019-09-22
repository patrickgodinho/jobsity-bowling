package com.jobsity.patrick;

import com.jobsity.patrick.service.BowlingService;
import com.jobsity.patrick.service.Printer;
import com.jobsity.patrick.validation.BowlingValidations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class App {

    public static void main(String[] args) {
        BowlingService bowlingService = new BowlingService();
//        BowlingValidations bowlingValidations = new BowlingValidations();
        Printer printer = new Printer();
        String fileName = "test.txt";

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            printer.printBoard(bowlingService.calcPlayScoresFromMap(bowlingService.buildPlayListFromStream(stream)));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
