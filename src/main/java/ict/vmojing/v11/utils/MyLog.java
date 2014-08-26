package ict.vmojing.v11.utils;

import org.apache.log4j.Logger;

public class MyLog {
	static Logger log = Logger.getLogger(MyLog.class.getName());

	public static void logDebug(String message) {
		log.debug(message);
	}

	public static void logInfo(String message) {
		log.info(message);
	}

	public static void logError(String message) {
		log.error(message);
	}
	public static void main(String[] args) {
		MyLog.logDebug("hello");
	}
}
