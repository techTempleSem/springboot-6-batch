package com.example.batch.batch;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Objects;

public class TaskletJob extends AbstractJob {
    private final Tasklet tasklet;

    @Builder
    public TaskletJob(ItemReader<?> itemReader, ItemProcessor<?, ?> itemProcessor, ItemWriter<?> itemWriter, JobExecutionListener jobExecutionListener){
        super(jobExecutionListener);
        this.tasklet = new SimpleTasklet(itemReader, itemProcessor, itemWriter);
    }

    public TaskletJob(Tasklet tasklet) {
        super(null);
        this.tasklet = tasklet;
    }

    @Override
    public void doExecute() {
        tasklet.execute();
    }
}
