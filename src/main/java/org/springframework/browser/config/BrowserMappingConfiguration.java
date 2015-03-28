package org.springframework.browser.config;

import java.util.ArrayList;
import java.util.List;

public class BrowserMappingConfiguration {
	private List<BrowserMapping> browsers;

	public BrowserMappingConfiguration() {
		this(new ArrayList<BrowserMapping>());
	}
	
	public BrowserMappingConfiguration(List<BrowserMapping> browsers) {
		super();
		this.browsers = browsers;
	}

	public List<BrowserMapping> getBrowsers() {
		return browsers;
	}

	public void setBrowsers(List<BrowserMapping> browsers) {
		this.browsers = browsers;
	}
}
