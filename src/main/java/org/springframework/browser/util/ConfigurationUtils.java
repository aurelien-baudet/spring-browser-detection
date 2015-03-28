package org.springframework.browser.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Pattern;

import org.springframework.browser.config.BrowserMapping;
import org.springframework.browser.config.BrowserMappingConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class ConfigurationUtils {
	public static final String DEFAULT_BROWSER_PROPERTY_PREFIX = "spring.browsers";
	public static final String DEFAULT_BROWSER_PROPERTY_FILE = "/browsers.properties";

	public static BrowserMappingConfiguration load() throws IOException {
		return load(new ClassPathResource(DEFAULT_BROWSER_PROPERTY_FILE));
	}

	public static BrowserMappingConfiguration load(Resource props) throws IOException {
		return fromProperties(PropertiesLoaderUtils.loadProperties(props));
	}

	public static BrowserMappingConfiguration fromProperties(Properties props) {
		return fromProperties(DEFAULT_BROWSER_PROPERTY_PREFIX, props);
	}

	public static BrowserMappingConfiguration fromProperties(String prefix, Properties props) {
		List<BrowserMapping> browsers = new ArrayList<BrowserMapping>();
		for(Entry<Object, Object> prop : props.entrySet()) {
			if(prop.getKey()!=null) {
				String key = prop.getKey().toString();
				if(key.startsWith(prefix) && key.endsWith("pattern") && prop.getValue()!=null) {
					Pattern pattern = Pattern.compile(prop.getValue().toString());
					String nameProp = props.getProperty(key.replace("pattern", "name"));
					String overrideName = nameProp==null || nameProp.isEmpty() ? null : nameProp;
					String vendorPrefix = props.getProperty(key.replace("pattern", "vendorPrefix"), "");
					browsers.add(new BrowserMapping(pattern, overrideName, vendorPrefix));
				}
			}
		}
		return new BrowserMappingConfiguration(browsers);
	}
}
