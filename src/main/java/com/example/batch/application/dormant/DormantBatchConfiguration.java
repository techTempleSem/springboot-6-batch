package com.example.batch.application.dormant;

import com.example.batch.batch.Job;
import com.example.batch.batch.Step;
import com.example.batch.batch.StepJobBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DormantBatchConfiguration {
    @Bean
    public Job dormantBatchJob(
            Step preDormantBatchStep,
            Step dormantBatchStep,
            DormantBatchJobExecutionListener listener
    ){
        return new StepJobBuilder()
                .start(preDormantBatchStep)
                .next(dormantBatchStep)
                .listener(listener).build();
    }

    @Bean
    public Step dormantBatchStep(
            AllCustomerItemReader dormantBatchItemReader,
            DormantBatchItemProcessor dormantBatchItemProcessor,
            DormantBatchItemWriter dormantBatchItemWriter
    ){
        return Step.builder()
                .itemReader(dormantBatchItemReader)
                .itemProcessor(dormantBatchItemProcessor)
                .itemWriter(dormantBatchItemWriter).build();
    }

    @Bean
    public Step preDormantBatchStep(
            AllCustomerItemReader reader,
            PreDormantBatchProcessor processor,
            PreDormantBatchWriter writer
    ){
        return Step.builder()
                .itemReader(reader)
                .itemProcessor(processor)
                .itemWriter(writer).build();
    }
}
