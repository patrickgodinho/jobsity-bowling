package com.jobsity.patrick;

import com.jobsity.patrick.model.Frame;
import com.jobsity.patrick.service.BowlingService;
import com.jobsity.patrick.service.FileReaderService;
import com.jobsity.patrick.service.PrinterService;
import com.jobsity.patrick.service.impl.BowlingServiceImpl;
import com.jobsity.patrick.service.impl.FileReaderServiceImpl;
import com.jobsity.patrick.service.impl.PrinterServiceImpl;
import com.jobsity.patrick.validation.BowlingValidations;
import com.jobsity.patrick.validation.impl.BowlingValidationsImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


public class App {

    public static void main(String args[]) {
        BowlingService bowlingService = new BowlingServiceImpl();
        BowlingValidations bowlingValidations = new BowlingValidationsImpl();
        PrinterService printerService = new PrinterServiceImpl();
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        if(args.length==0){
            System.err.println("ERROR!! Please run the command with filename, ie:\nmvn compile && mvn exec:java -Dexec.args=\"real.txt\"");
            return;
        }
        Stream<String> lines = fileReaderService.readFile(args[0]);

            Map<String, List<Frame>> playsMap =
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
