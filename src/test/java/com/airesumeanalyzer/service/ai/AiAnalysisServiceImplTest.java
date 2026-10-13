package com.airesumeanalyzer.service.ai;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.airesumeanalyzer.dto.AnalysisResultDto;
import org.junit.jupiter.api.Test;

class AiAnalysisServiceImplTest {
    private final AiAnalysisServiceImpl service = new AiAnalysisServiceImpl();

    @Test
    void doesNotCountJobOnlySkillsAsResumeSkills() {
        AnalysisResultDto result = service.analyze(
            "Java developer. Skills: Java, SQL, Git.",
            "Required skills: Java, SQL, AWS, Docker."
        );

        assertEquals(3, result.getSkills().size());
        assertFalse(result.getSkills().contains("aws"));
        assertFalse(result.getSkills().contains("docker"));
        assertEquals(2, result.getMissingSkills().size());
    }

    @Test
    void keepsResumeScoreBetweenSixtyAndEighty() {
        AnalysisResultDto result = service.analyze(
            "Java developer. Skills: Java, SQL, Git. Education, project, experience, summary.",
            "Required skills: Java, SQL, AWS, Docker."
        );

        assertTrue(result.getScore() >= 60 && result.getScore() <= 80);
    }

    @Test
    void resumeScoresRespondToResumeContent() {
        AnalysisResultDto shortResume = service.analyze("Java developer.", null);
        AnalysisResultDto detailedResume = service.analyze(
            "Professional summary. Education: bachelor's degree. Experience and projects. "
                + "Skills: Java, JavaScript, Python, React, SQL, MySQL, Git, Docker. "
                + "Certification earned. Improved performance by 25%. "
                + "Delivered multiple projects with measurable results and optimized systems.",
            null
        );

        assertTrue(detailedResume.getScore() > shortResume.getScore());
    }
}
