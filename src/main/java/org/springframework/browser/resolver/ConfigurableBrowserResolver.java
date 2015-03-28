package org.springframework.browser.resolver;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.browser.Browser;
import org.springframework.browser.SimpleBrowser;
import org.springframework.browser.config.BrowserMapping;
import org.springframework.browser.config.BrowserMappingConfiguration;

/**
 * Resolver that makes the mapping between a user-agent and a browser based on
 * configuration properties. This implementation delegates the mapping to
 * {@link RegexBrowserResolver}
 * 
 * @author Aurélien Baudet
 *
 */
public class ConfigurableBrowserResolver implements UserAgentBrowserResolver {
	private RegexBrowserResolver delegate;

	public ConfigurableBrowserResolver(BrowserMappingConfiguration configuration) {
		super();
		delegate = new RegexBrowserResolver(toMap(configuration));
	}

	private Map<Pattern, Browser> toMap(BrowserMappingConfiguration configuration) {
		Map<Pattern, Browser> map = new HashMap<Pattern, Browser>();
		for(BrowserMapping mapping : configuration.getBrowsers()) {
			map.put(mapping.getPattern(), new SimpleBrowser(mapping.getName(), null, mapping.getVendorPrefix()));
		}
		return map;
	}

	public Browser resolve(String userAgent) {
		return delegate.resolve(userAgent);
	}

}
