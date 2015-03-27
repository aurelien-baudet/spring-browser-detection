package org.springframework.browser.resolver;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.browser.Browser;
import org.springframework.browser.SimpleBrowser;

/**
 * Detect browser according to user agent based on a map that apply regular
 * expressions on the user agent in order to find which one represents the
 * browser
 * 
 * @author Aurélien Baudet
 *
 */
public class RegexBrowserResolver implements UserAgentBrowserResolver {

	private Map<Pattern, Browser> patterns;

	public RegexBrowserResolver(Map<Pattern, Browser> patterns) {
		super();
		this.patterns = patterns;
	}

	public Browser resolve(String userAgent) {
		for (Pattern pattern : patterns.keySet()) {
			Matcher matcher = pattern.matcher(userAgent);
			if (matcher.find()) {
				String name = matcher.group("name");
				String version = matcher.group("version");
				Browser override = patterns.get(pattern);
				return new SimpleBrowser(override.getName() == null ? name : override.getName(), override.getVersion() == null ? version : override.getVersion(), override.getVendorPrefix());
			}
		}
		return null;
	}

}
