package com.example.batch.application;

import com.example.batch.batch.Job;
import com.example.batch.batch.SimpleTasklet;
import com.example.batch.customer.Customer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DormantBatchConfiguration {
    @Bean
    public Job dormantBatchJob(
            DormantBatchItemReader dormantBatchItemReader,
            DormantBatchItemProcessor dormantBatchItemProcessor,
            DormantBatchItemWriter dormantBatchItemWriter,
            DormantBatchJobExecutionListener listener
    ){
        return Job.builder()
                .itemReader(dormantBatchItemReader)
                .itemProcessor(dormantBatchItemProcessor)
                .itemWriter(dormantBatchItemWriter)
                .jobExecutionListener(listener).build();
    }
}
