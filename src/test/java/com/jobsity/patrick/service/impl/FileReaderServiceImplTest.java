package com.jobsity.patrick.service.impl;

import com.jobsity.patrick.service.FileReaderService;
import junit.framework.Assert;
import junit.framework.TestCase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;




import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReaderServiceImplTest extends TestCase {

    public void testReadFile() {
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        Stream<String> plays = fileReaderService.readFile("real.txt");
        List<String> playList = plays.collect(Collectors.toList());
        Assert.assertNotNull(plays);
        assertThat(playList, hasItems("Jeff 10"));
    }
}