package com.example.batch;

import com.example.batch.batch.BatchStatus;
import com.example.batch.batch.Job;
import com.example.batch.batch.JobExecution;
import com.example.batch.customer.Customer;
import com.example.batch.customer.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
class DormantBatchJobTest {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private Job dormantBatchJob;

    @BeforeEach
    public void setUp(){
        customerRepository.deleteAll();
    }
    
    @Test
    @DisplayName("로그인 시간이 1년 넘은 고객 3명 휴면 전환")
    void test1(){

        saveCustomer(370);
        saveCustomer(370);
        saveCustomer(370);
        saveCustomer(350);
        saveCustomer(350);
        saveCustomer(350);
        saveCustomer(350);
        saveCustomer(350);

        final JobExecution result = dormantBatchJob.execute();

        final long dormantCount = customerRepository.findAll()
                .stream()
                .filter(it -> it.getStatus() == Customer.Status.DORMANT)
                .count();

        Assertions.assertThat(dormantCount).isEqualTo(3);
        Assertions.assertThat(result.getStatus()).isEqualTo(BatchStatus.COMPLETED);
    }

    @Test
    @DisplayName("휴면 전환 대상 10명")
    void test2(){
        saveCustomer(400);
        saveCustomer(400);
        saveCustomer(400);
        saveCustomer(400);
        saveCustomer(400);
        saveCustomer(400);
        saveCustomer(400);
        saveCustomer(400);
        saveCustomer(400);
        saveCustomer(400);

        final JobExecution result = dormantBatchJob.execute();

        final long dormantCount = customerRepository.findAll()
                .stream()
                .filter(it -> it.getStatus() == Customer.Status.DORMANT)
                .count();

        Assertions.assertThat(dormantCount).isEqualTo(10);
        Assertions.assertThat(result.getStatus()).isEqualTo(BatchStatus.COMPLETED);
    }

    @Test
    @DisplayName("고객 0명")
    void test3(){
        final JobExecution result = dormantBatchJob.execute();

        final long dormantCount = customerRepository.findAll()
                .stream()
                .filter(it -> it.getStatus() == Customer.Status.DORMANT)
                .count();

        Assertions.assertThat(dormantCount).isEqualTo(0);
        Assertions.assertThat(result.getStatus()).isEqualTo(BatchStatus.COMPLETED);
    }

    @Test
    @DisplayName("배치 실패")
    void test4(){
        final Job dormantBatchJob = new Job(null, null);

        final JobExecution result = dormantBatchJob.execute();

        Assertions.assertThat(result.getStatus()).isEqualTo(BatchStatus.FAILED);
    }

    private void saveCustomer(long loginMinusDays) {
        final String uuid = UUID.randomUUID().toString();
        final Customer test = new Customer(uuid, uuid + "@test.test");
        test.setLoginAt(LocalDateTime.now().minusDays(loginMinusDays));
        customerRepository.save(test);
    }
}