package com.example.batch;

import lombok.extern.slf4j.Slf4j;

public interface EmailProvider {
    void send(String emailAddr, String title, String body);

    @Slf4j
    class Fake implements EmailProvider{

        @Override
        public void send(String emailAddr, String title, String body) {
            log.info("{} email 전송 완료! {} : {}",emailAddr,title,body);
        }
    }
}
