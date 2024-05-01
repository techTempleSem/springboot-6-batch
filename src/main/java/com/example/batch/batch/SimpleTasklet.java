package com.example.batch.batch;

import com.example.batch.batch.ItemProcessor;
import com.example.batch.batch.ItemReader;
import com.example.batch.batch.ItemWriter;
import com.example.batch.batch.Tasklet;
import com.example.batch.customer.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

public class SimpleTasklet<T, D> implements Tasklet {

    private final ItemReader<T> itemReader;
    private final ItemProcessor<T, D> itemProcessor;
    private final ItemWriter<D> itemWriter;

    public SimpleTasklet(ItemReader<T> itemReader, ItemProcessor<T, D> itemProcessor, ItemWriter<D> itemWriter) {
        this.itemReader = itemReader;
        this.itemProcessor = itemProcessor;
        this.itemWriter = itemWriter;
    }

    @Override
    public void execute() {
        while (true) {
            final T read = itemReader.read();
            if(read == null) break;
            // 조회


            final D process = itemProcessor.process(read);
            if(process == null) continue;
            // 휴면 계정 대상 추출


            itemWriter.write(process);
            // 휴면 계정으로 전환
        }
    }
}
