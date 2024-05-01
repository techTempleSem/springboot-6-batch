package com.example.batch.application.step;

import com.example.batch.batch.Job;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StepBatchConfigurationTest {
    @Autowired
    private Job stepBatchJob;

    @Test
    void test(){
        stepBatchJob.execute();
    }
}