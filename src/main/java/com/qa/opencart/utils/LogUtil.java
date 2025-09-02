package com.qa.opencart.utils;

import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.qa.opencart.factory.DriverFactory;

public class LogUtil {
	public static final org.apache.logging.log4j.Logger log = LogManager.getLogger(LogUtil.class);
	
	public static void info(String mesg) {
		log.info(mesg);
	}
	
	public static void warn(String mesg) {
		log.warn(mesg);
	}
	
	public static void error(String mesg) {
		log.error(mesg);
	}
	
	public static void fatal(String mesg) {
		log.fatal(mesg);
	}

}
