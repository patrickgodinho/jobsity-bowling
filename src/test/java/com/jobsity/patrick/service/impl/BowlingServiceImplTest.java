package com.jobsity.patrick.service.impl;

import com.jobsity.patrick.model.Frame;
import com.jobsity.patrick.service.BowlingService;
import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class BowlingServiceImplTest extends TestCase {

    public void testBuildPlayListFromStream() {
        BowlingService bowlingService = new BowlingServiceImpl();
        List<String> lines = new ArrayList<>();
        lines.add("Jeff 10");
        lines.add("John 3");
        lines.add("John 7");
        lines.add("Jeff 7");
        lines.add("Jeff 3");
        lines.add("John 6");
        lines.add("John 3");
        lines.add("Jeff 9");
        lines.add("Jeff 0");
        lines.add("John 10");
        lines.add("Jeff 10");
        lines.add("John 8");
        lines.add("John 1");
        lines.add("Jeff 0");
        lines.add("Jeff 8");
        lines.add("John 10");
        lines.add("Jeff 8");
        lines.add("Jeff 2");
        lines.add("John 10");
        lines.add("Jeff F");
        lines.add("Jeff 6");
        lines.add("John 9");
        lines.add("John 0");
        lines.add("Jeff 10");
        lines.add("John 7");
        lines.add("John 3");
        lines.add("Jeff 10");
        lines.add("John 4");
        lines.add("John 4");
        lines.add("Jeff 10");
        lines.add("Jeff 8");
        lines.add("Jeff 1");
        lines.add("John 10");
        lines.add("John 9");
        lines.add("John 0");

        Map<String, List<Frame>> plays = bowlingService.buildPlayListFromStream(lines.stream());
        assertThat(plays.size(), is(2));
        plays.forEach((name,values)->{
            Assert.assertTrue(values.size()==10);
        });

        plays = bowlingService.calcPlayScoresFromMap(plays);

        plays.forEach((name,values)->{
            if(name.equals("Jeff")) assertThat(values, hasItem(hasProperty("firstScore", is(10))));
            if(name.equals("Jeff")) assertThat(values, hasItem(hasProperty("secondScore", is(8))));
            if(name.equals("Jeff")) assertThat(values, hasItem(hasProperty("finalScore", is(1))));
            if(name.equals("Jeff")) assertThat(values, hasItem(hasProperty("totalScore", is(167))));
        });
    }


}