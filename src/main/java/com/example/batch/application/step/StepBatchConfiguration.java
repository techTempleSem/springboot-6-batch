package com.example.batch.application.step;

import com.example.batch.batch.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class StepBatchConfiguration {
    @Bean
    public Job stepBatchJob(
            Step step1,
            Step step2,
            Step step3
    ){
        return new StepJobBuilder()
                .start(step1)
                .next(step2)
                .next(step3)
                .build();
    }

    @Bean
    public Step step1(){
        final Tasklet tasklet = () -> System.out.println("step1");
        return new Step(tasklet);
    }

    @Bean
    public Step step2(){
        final Tasklet tasklet = () -> System.out.println("step2");
        return new Step(tasklet);
    }

    @Bean
    public Step step3(){
        final Tasklet tasklet = () -> System.out.println("step3");
        return new Step(tasklet);
    }
}
