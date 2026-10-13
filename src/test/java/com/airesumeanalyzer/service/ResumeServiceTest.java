package com.airesumeanalyzer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ResumeServiceTest {
    @Test
    void mapsJobSkillCoverageIntoRequestedScoreRange() {
        assertEquals(49, ResumeService.calculateJobMatchScore(0, 6));
        assertEquals(66, ResumeService.calculateJobMatchScore(3, 6));
        assertEquals(83, ResumeService.calculateJobMatchScore(6, 6));
    }

    @Test
    void usesMinimumJobMatchWhenNoRequiredSkillsWereDetected() {
        assertEquals(49, ResumeService.calculateJobMatchScore(0, 0));
    }

    @Test
    void changesRepeatedScoreWithoutLeavingRequestedRange() {
        assertEquals(67, ResumeService.ensureScoreChanges(66, 66));
        assertEquals(82, ResumeService.ensureScoreChanges(83, 83));
        assertEquals(66, ResumeService.ensureScoreChanges(66, 65));
        assertEquals(66, ResumeService.ensureScoreChanges(66, null));
    }

    @Test
    void changesRepeatedResumeScoreWithinSixtyToEightyRange() {
        assertEquals(61, ResumeService.ensureScoreChanges(60, 60, 60, 80));
        assertEquals(79, ResumeService.ensureScoreChanges(80, 80, 60, 80));
        assertEquals(72, ResumeService.ensureScoreChanges(72, 71, 60, 80));
    }
}
