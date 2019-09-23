package com.jobsity.patrick.service.impl;

import com.jobsity.patrick.service.FileReaderService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReaderServiceImpl implements FileReaderService {
    @Override
    public Stream<String> readFile(String file) {
        Stream<String> lines = Stream.empty();
        try {
            lines = Files.lines(Paths.get(file));
        }catch (IOException e){
            e.printStackTrace();
        }
        return lines;
    }
}
