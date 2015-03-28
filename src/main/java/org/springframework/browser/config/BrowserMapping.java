package org.springframework.browser.config;

import java.util.regex.Pattern;

public class BrowserMapping {
	private Pattern pattern;
	
	private String name;
	
	private String vendorPrefix;

	public BrowserMapping() {
		super();
	}

	public BrowserMapping(Pattern pattern, String name, String vendorPrefix) {
		super();
		this.pattern = pattern;
		this.name = name;
		this.vendorPrefix = vendorPrefix;
	}

	public Pattern getPattern() {
		return pattern;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVendorPrefix() {
		return vendorPrefix;
	}

	public void setVendorPrefix(String vendorPrefix) {
		this.vendorPrefix = vendorPrefix;
	}
}
