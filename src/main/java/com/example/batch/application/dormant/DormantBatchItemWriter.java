package com.example.batch.application.dormant;

import com.example.batch.EmailProvider;
import com.example.batch.batch.ItemWriter;
import com.example.batch.customer.Customer;
import com.example.batch.customer.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class DormantBatchItemWriter implements ItemWriter<Customer> {
    private final CustomerRepository customerRepository;
    private final EmailProvider emailProvider;

    public DormantBatchItemWriter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.emailProvider = new EmailProvider.Fake();
    }

    @Override
    public void write(Customer item) {
        customerRepository.save(item);
        emailProvider.send(item.getEmail(), "휴면 전환 안내", "내용");
    }
}
