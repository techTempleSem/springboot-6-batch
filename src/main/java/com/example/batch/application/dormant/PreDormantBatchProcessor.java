package com.example.batch.application.dormant;

import com.example.batch.batch.ItemProcessor;
import com.example.batch.customer.Customer;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PreDormantBatchProcessor implements ItemProcessor<Customer, Customer> {
    @Override
    public Customer process(Customer item) {
        final boolean isDormantTarget = LocalDate.now()
                .minusDays(358)
                .equals(item.getLoginAt().toLocalDate());

        if (isDormantTarget) {
            return item;
        } else {
            return null;
        }
    }
}
