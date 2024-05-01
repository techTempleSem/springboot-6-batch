package com.example.batch.application.dormant;

import com.example.batch.EmailProvider;
import com.example.batch.batch.JobExecution;
import com.example.batch.batch.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class DormantBatchJobExecutionListener implements JobExecutionListener {
    private final EmailProvider emailProvider;

    public DormantBatchJobExecutionListener() {
        this.emailProvider = new EmailProvider.Fake();
    }

    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        emailProvider.send("admin@test.test","batch","job 수행. status : "+jobExecution.getStatus());
    }
}
