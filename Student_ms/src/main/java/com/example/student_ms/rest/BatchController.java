package com.example.student_ms.rest;

import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.parameters.JobParameters;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/batch")
public class BatchController {


    private final JobLauncher jobLauncher; // ← It works, despite deprecation warning

    private final Job generateReleveDeNotesJob;

    public BatchController(JobLauncher jobLauncher, Job generateReleveDeNotesJob) {
        this.jobLauncher = jobLauncher;
        this.generateReleveDeNotesJob = generateReleveDeNotesJob;
    }

    @PostMapping("/generate-releves")
    public String generateReleves() throws Exception {
        JobParameters params = new JobParametersBuilder()
                .addLong("time", System.nanoTime())
                .toJobParameters();

        // This still works in Spring Batch 6.x
        jobLauncher.run(generateReleveDeNotesJob, params);
        return "Job started!";
    }
}