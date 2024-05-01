package com.example.batch.batch;

public interface ItemProcessor<T, D> {
    D process(T item);
}
