package com.jobsity.patrick.service;

import java.util.stream.Stream;

public interface FileReaderService {
     Stream<String> readFile(String file);
}
