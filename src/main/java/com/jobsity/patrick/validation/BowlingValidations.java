package com.jobsity.patrick.validation;

import com.jobsity.patrick.model.Play;

import java.util.List;

public interface BowlingValidations {
    String validate(List<Play> plays);
}
