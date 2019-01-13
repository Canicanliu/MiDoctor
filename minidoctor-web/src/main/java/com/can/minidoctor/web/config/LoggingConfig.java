package com.can.minidoctor.web.config;

import ch.qos.logback.classic.AsyncAppender;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.spi.ContextAwareBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;



public class LoggingConfig {

    private final Logger log = LoggerFactory.getLogger(LoggingConfig.class);

    //private LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

    private String pre="D:\\logs\\";


    public LoggerContext getLoggerContext(){
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        addLogstashAppender(context);
        // Add context listener
        LogbackLoggerContextListener loggerContextListener = new LogbackLoggerContextListener();
        loggerContextListener.setContext(context);
        context.addListener(loggerContextListener);
        return context;
    }

//    @PostConstruct
//    private void init() {
//        addLogstashAppender(context);
//        // Add context listener
//        LogbackLoggerContextListener loggerContextListener = new LogbackLoggerContextListener();
//        loggerContextListener.setContext(context);
//        context.addListener(loggerContextListener);
//    }

    public void addLogstashAppender(LoggerContext context) {
        log.info("Initializing Logstash logging");

        RollingFileAppender fileAppender=new RollingFileAppender();
        fileAppender.setFile(pre+"web.log");

        PatternLayoutEncoder encoder=new PatternLayoutEncoder();
        encoder.setPattern("%d{yyyy-MM-dd HH:mm:ss.SSS} [%X{requestId}] %-5level %logger{-1} - %msg%n");
        fileAppender.setEncoder(encoder);

        TimeBasedRollingPolicy rollingPolicy=new TimeBasedRollingPolicy();
        rollingPolicy.setFileNamePattern(pre+"web%d{yyyy-MM-dd}.log.gz");
        fileAppender.setRollingPolicy(rollingPolicy);

        ConsoleAppender consoleAppender=new ConsoleAppender();
        consoleAppender.setEncoder(encoder);

        fileAppender.start();
        consoleAppender.start();
        context.getLogger("ROOT").addAppender(fileAppender);
        context.getLogger("ROOT").addAppender(consoleAppender);
    }

    /**
     * Logback configuration is achieved by configuration file and API.
     * When configuration file change is detected, the configuration is reset.
     * This listener ensures that the programmatic configuration is also re-applied after reset.
     */
    class LogbackLoggerContextListener extends ContextAwareBase implements LoggerContextListener {

        @Override
        public boolean isResetResistant() {
            return true;
        }

        @Override
        public void onStart(LoggerContext context) {
            addLogstashAppender(context);
        }

        @Override
        public void onReset(LoggerContext context) {
            addLogstashAppender(context);
        }

        @Override
        public void onStop(LoggerContext context) {
        }

        @Override
        public void onLevelChange(ch.qos.logback.classic.Logger logger, Level level) {
        }
    }



}
