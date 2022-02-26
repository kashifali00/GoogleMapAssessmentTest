package com.qa.Utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class Log {

    static {
        String log4jPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
            + File.separator + "resources" + File.separator + "log4j.xml";
        System.out.println("Log4jPATH ----> " + log4jPath);
        System.setProperty("logoutputpath",System.getProperty("user.dir"));
        System.setProperty("log4j.ConfigurationFile",log4jPath);
    }

    public static Logger Log = LogManager.getLogger(Log.class.getClass());
}
