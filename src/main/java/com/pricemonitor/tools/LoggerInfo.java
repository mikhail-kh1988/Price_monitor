package com.pricemonitor.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerInfo {

    private Class clazz;
    private Logger logger;

    public LoggerInfo(Class clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
        this.clazz = clazz;
    }

    public void info(String message){
        logger.info(message);
    }

    public void debug(String message){
        logger.debug(message);
    }

    public void trace(String message){
        logger.trace(message);
    }

    public void warn(String message){
        logger.warn(message);
    }

    public void error(String message){
        logger.error(message);
    }

}
