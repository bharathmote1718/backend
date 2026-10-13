package com.airesumeanalyzer.repository;

import com.airesumeanalyzer.entity.Resume;
import com.airesumeanalyzer.entity.ResumeAnalysis;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeAnalysisRepository extends JpaRepository<ResumeAnalysis, Long> {
    Optional<ResumeAnalysis> findTopByResumeOrderByCreatedAtDesc(Resume resume);
    Optional<ResumeAnalysis> findTopByResume_User_IdOrderByCreatedAtDescIdDesc(Long userId);
    void deleteByResume(Resume resume);
}
