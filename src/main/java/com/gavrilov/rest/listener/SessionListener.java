package com.gavrilov.rest.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    private static final Logger log = LoggerFactory.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info("Создание новой сессии");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("Уничтожение сессии");
    }
}
