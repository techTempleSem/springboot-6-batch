package com.example.batch.application.dormant;

import com.example.batch.EmailProvider;
import com.example.batch.batch.ItemWriter;
import com.example.batch.customer.Customer;
import com.example.batch.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PreDormantBatchWriter implements ItemWriter<Customer> {
    private final EmailProvider emailProvider;

    @Autowired
    public PreDormantBatchWriter() {
        this.emailProvider = new EmailProvider.Fake();
    }

    public PreDormantBatchWriter(EmailProvider emailProvider) {
        this.emailProvider = emailProvider;
    }

    @Override
    public void write(Customer item) {
        emailProvider.send(item.getEmail(), "휴면 전환 안내", "내용");
    }
}
