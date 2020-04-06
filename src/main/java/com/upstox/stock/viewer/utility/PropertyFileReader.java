package com.upstox.stock.viewer.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author shishir.sarkar
 * This class reads the env data from properties file
 */
public class PropertyFileReader {

	private final Properties configProp = new Properties();
	private Logger log = LoggerFactory.getLogger(PropertyFileReader.class);

	private PropertyFileReader() {
		// Private constructor to restrict new instances
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("application.properties");
		log.info("Read all properties from file");
		try {
			configProp.load(in);
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	private static class LazyHolder {
		private static final PropertyFileReader INSTANCE = new PropertyFileReader();
	}

	public static PropertyFileReader getInstance() {
		return LazyHolder.INSTANCE;
	}

	public String getProperty(String key) {
		return configProp.getProperty(key);
	}

}
