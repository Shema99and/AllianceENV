package com.zifo.ewb.alliance.utils;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

/**
 * Utility class to read the properties from application.properties
 * 
 * @author Zifo
 *
 */
@Component
@NoArgsConstructor
public class ConfigResources {
	/**
	 * logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigResources.class.getName());
	/**
	 * activeconfig property
	 */
	private String activeconfig;
	private static String tlsVersion;

	/**
	 * 
	 */
	public void load() {
		final ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
		activeconfig = resourceBundle.getString("active.config");
		tlsVersion = resourceBundle.getString("tls_version");
		if (LOGGER.isDebugEnabled()) {
//			LOGGER.debug("Active config is " + activeconfig);
			LOGGER.debug("Active config is {}", activeconfig);
		}
	}

	public String getActiveconfig() {
		return activeconfig;
	}

	/**
	 * @param activeconfig
	 */
	public void setActiveconfig(final String activeconfig) {
		this.activeconfig = activeconfig;
	}
	
	public static String getTLSVersion() {
		return tlsVersion;
	}

	/**
	 * @param activeconfig
	 */
	public void setTLSVersion(final String tlsVersion) {
		this.tlsVersion = tlsVersion;
	}

}
