package org.fsarmiento.invoicing.util;

import org.slf4j.Logger;

/**
 * The Class LoggerUtil.
 * 
 * @author Florencio Sarmiento
 * @since 1.0
 */
public class LoggerUtil {

	/**
	 * The Enum Level.
	 * 
	 * @author Florencio Sarmiento
	 * @since
	 */
	public enum Level {

		DEBUG, INFO, TRACE, WARN, ERROR;

	}

	/**
	 * Debug.
	 * 
	 * @param logger
	 *            the logger
	 * @param text
	 *            the text
	 */
	public static void debug(Logger logger, String text) {
		if (logger.isDebugEnabled()) {
			logger.debug(text);
		}
	}

	/**
	 * Info.
	 * 
	 * @param logger
	 *            the logger
	 * @param text
	 *            the text
	 */
	public static void info(Logger logger, String text) {
		if (logger.isInfoEnabled()) {
			logger.info(text);
		}
	}

	/**
	 * Trace.
	 * 
	 * @param logger
	 *            the logger
	 * @param text
	 *            the text
	 */
	public static void trace(Logger logger, String text) {
		if (logger.isTraceEnabled()) {
			logger.trace(text);
		}
	}

	/**
	 * Warn.
	 * 
	 * @param logger
	 *            the logger
	 * @param text
	 *            the text
	 */
	public static void warn(Logger logger, String text) {
		if (logger.isWarnEnabled()) {
			logger.warn(text);
		}
	}

	/**
	 * Error.
	 * 
	 * @param logger
	 *            the logger
	 * @param text
	 *            the text
	 */
	public static void error(Logger logger, String text) {
		if (logger.isErrorEnabled()) {
			logger.error(text);
		}
	}

	/**
	 * Log.
	 * 
	 * @param logger
	 *            the logger
	 * @param text
	 *            the text
	 * @param level
	 *            the level
	 */
	public static void log(Logger logger, String text, Level level) {

		switch (level) {
		case DEBUG:
			debug(logger, text);
			break;

		case INFO:
			info(logger, text);
			break;

		case TRACE:
			trace(logger, text);
			break;

		case WARN:
			warn(logger, text);
			break;

		case ERROR:
			error(logger, text);
			break;
		}
	}
}
