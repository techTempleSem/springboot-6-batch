package com.example.batch.application.dormant;

import com.example.batch.customer.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


class PreDormantBatchProcessorTest {
    private PreDormantBatchProcessor preDormantBatchProcessor;
    @BeforeEach
    void setup(){
        preDormantBatchProcessor = new PreDormantBatchProcessor();
    }
    @Test
    @DisplayName("358일 후")
    void test1(){
        final Customer customer = new Customer("test","test@test.test");
        customer.setLoginAt(LocalDateTime.now().minusDays(358));

        final Customer result = preDormantBatchProcessor.process(customer);

        Assertions.assertThat(result).isEqualTo(customer);

    }

    @Test
    @DisplayName("358일 전")
    void test2(){
        final Customer customer = new Customer("test","test@test.test");
        customer.setLoginAt(LocalDateTime.now().minusDays(350));

        final Customer result = preDormantBatchProcessor.process(customer);

        Assertions.assertThat(result).isEqualTo(null);
    }

}