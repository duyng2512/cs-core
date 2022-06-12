package com.dng.cs.core.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFactory {
    static public Logger systemLog(){ return LoggerFactory.getLogger("System");}
    static public Logger errorLog(){ return LoggerFactory.getLogger("Error");}
    static public Logger infoLog(){ return LoggerFactory.getLogger("Info");}
}
