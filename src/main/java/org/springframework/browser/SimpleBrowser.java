package org.springframework.browser;

public class SimpleBrowser implements Browser {

	private final String name;
	
	private final String version;
	
	private final String vendorPrefix;
	
	public SimpleBrowser(String name, String version, String vendorPrefix) {
		super();
		this.name = name;
		this.version = version;
		this.vendorPrefix = vendorPrefix;
	}

	public String getName() {
		return name;
	}

	public String getVersion() {
		return version;
	}

	public String getVendorPrefix() {
		return vendorPrefix;
	}

}
