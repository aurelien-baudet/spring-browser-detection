package org.springframework.browser.resolver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Pattern;

import org.springframework.browser.Browser;
import org.springframework.browser.SimpleBrowser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

/**
 * Resolver that makes the mapping between a user-agent and a browser based on
 * configuration properties. This implementation delegates the mapping to
 * {@link RegexBrowserResolver}
 * 
 * @author Aurélien Baudet
 *
 */
public class PropertiesBrowserResolver implements UserAgentBrowserResolver {
	public static final String DEFAULT_BROWSER_PROPERTY_PREFIX = "spring.browser";
	public static final String DEFAULT_BROWSER_PROPERTY_FILE = "application.properties";
	
	private RegexBrowserResolver delegate;
	
	public PropertiesBrowserResolver() throws IOException {
		this(DEFAULT_BROWSER_PROPERTY_FILE);
	}

	public PropertiesBrowserResolver(Resource props) throws IOException {
		this(props, DEFAULT_BROWSER_PROPERTY_PREFIX);
	}
	
	public PropertiesBrowserResolver(Properties props) {
		this(props, DEFAULT_BROWSER_PROPERTY_PREFIX);
	}

	public PropertiesBrowserResolver(String prefix) throws IOException {
		this(DEFAULT_BROWSER_PROPERTY_FILE, prefix);
	}

	public PropertiesBrowserResolver(String propertyFile, String prefix) throws IOException {
		this(new ClassPathResource("/"+propertyFile), prefix);
	}

	public PropertiesBrowserResolver(Resource props, String prefix) throws IOException {
		this(PropertiesLoaderUtils.loadProperties(props));
	}

	public PropertiesBrowserResolver(Properties props, String prefix) {
		this(new RegexBrowserResolver(toMap(props, prefix)));
	}

	public PropertiesBrowserResolver(RegexBrowserResolver delegate) {
		super();
		this.delegate = delegate;
	}

	public Browser resolve(String userAgent) {
		return delegate.resolve(userAgent);
	}

	private static Map<Pattern, Browser> toMap(Properties props, String prefix) {
		Map<Pattern, Browser> map = new HashMap<Pattern, Browser>();
		for(Entry<Object, Object> prop : props.entrySet()) {
			if(prop.getKey()!=null) {
				String key = prop.getKey().toString();
				if(key.startsWith(prefix) && key.endsWith("pattern") && prop.getValue()!=null) {
					Pattern pattern = Pattern.compile(prop.getValue().toString());
					String nameProp = props.getProperty(key.replace("pattern", "name"));
					String overrideName = nameProp==null || nameProp.isEmpty() ? null : nameProp;
					map.put(pattern, new SimpleBrowser(overrideName, null, props.getProperty(key.replace("pattern", "vendor.prefix"), "")));
				}
			}
		}
		return map;
	}

}
