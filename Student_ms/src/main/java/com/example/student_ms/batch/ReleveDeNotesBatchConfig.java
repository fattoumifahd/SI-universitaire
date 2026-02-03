package com.example.student_ms.batch;

import com.example.student_ms.model.Student;
import com.example.student_ms.model.dtos.ReleveDeNotes;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.infrastructure.item.database.JpaPagingItemReader;
import org.springframework.batch.infrastructure.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
@EnableBatchProcessing
public class ReleveDeNotesBatchConfig {

    private final JobRepository jobRepository;

    public ReleveDeNotesBatchConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<Student> studentJpaPagingItemReader(EntityManagerFactory emf) {
        return new JpaPagingItemReaderBuilder<Student>()
                .name("studentReader")
                .entityManagerFactory(emf)
                .queryString("SELECT s FROM Student s WHERE s.id IN (SELECT DISTINCT g.studentId FROM StudentCourseGrade g)")
                .pageSize(5)
                .build();
    }

    @Bean
    public Step generateReleveStep(
            JpaPagingItemReader<Student> studentJpaPagingItemReader,
            ReleveDeNotesItemProcessor processor,      // @Component
            ReleveDeNotesWriter writer,               // @Component
            PlatformTransactionManager transactionManager) {

        return new StepBuilder("generateReleveStep", jobRepository)
                .<Student, ReleveDeNotes>chunk(5)
                .reader(studentJpaPagingItemReader)
                .processor(processor)
                .writer(writer)
                .transactionManager(transactionManager)
                .build();
    }

    @Bean
    public Job generateReleveDeNotesJob(Step generateReleveStep) {
        return new JobBuilder("generateReleveDeNotesJob", jobRepository)
                .start(generateReleveStep)
                .build();
    }
}