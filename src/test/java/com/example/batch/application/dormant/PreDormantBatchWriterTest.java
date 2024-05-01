package com.example.batch.application.dormant;

import com.example.batch.EmailProvider;
import com.example.batch.customer.Customer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PreDormantBatchWriterTest {
    private PreDormantBatchWriter preDormantBatchWriter;
    @Test
    @DisplayName("1주일 뒤 계정 전환 알림")
    void test1(){
        final EmailProvider mockEmail = mock(EmailProvider.class);
        final Customer customer = new Customer("test", "test@test.test");
        this.preDormantBatchWriter = new PreDormantBatchWriter(mockEmail);
        preDormantBatchWriter.write(customer);
        verify(mockEmail, atLeast(1)).send(any(),any(),any());
    }

}