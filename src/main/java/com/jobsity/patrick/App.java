package com.jobsity.patrick;

import com.jobsity.patrick.model.Play;
import com.jobsity.patrick.service.BowlingService;
import com.jobsity.patrick.service.FileReaderService;
import com.jobsity.patrick.service.PrinterService;
import com.jobsity.patrick.service.impl.BowlingServiceImpl;
import com.jobsity.patrick.service.impl.FileReaderServiceImpl;
import com.jobsity.patrick.service.impl.PrinterServiceImpl;
import com.jobsity.patrick.validation.BowlingValidations;
import com.jobsity.patrick.validation.impl.BowlingValidationsImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


public class App {

    public static void main(String args[]) {
        BowlingService bowlingService = new BowlingServiceImpl();
        BowlingValidations bowlingValidations = new BowlingValidationsImpl();
        PrinterService printerService = new PrinterServiceImpl();
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        Stream<String> lines = fileReaderService.readFile(args[0]);

            Map<String, List<Play>> playsMap =
                    bowlingService.calcPlayScoresFromMap(
                            bowlingService.buildPlayListFromStream(lines));

            playsMap.forEach((name,plays)->{
                String error = bowlingValidations.validate(plays);
                if(!error.isEmpty()){
                    throw new IllegalArgumentException(name+": "+error);
                }
            });

            printerService.printBoard(playsMap);

    }
}
