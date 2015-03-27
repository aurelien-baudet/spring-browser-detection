package org.springframework.browser;

public interface Browser {
	public String getName();
	
	public String getVersion();
	
	public String getVendorPrefix();
}
