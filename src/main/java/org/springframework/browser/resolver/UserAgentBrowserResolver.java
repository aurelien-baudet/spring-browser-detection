package org.springframework.browser.resolver;

import org.springframework.browser.Browser;

/**
 * Resolver that find which browser is used according to the provided user agent
 * 
 * @author Aurélien Baudet
 *
 */
public interface UserAgentBrowserResolver {
	/**
	 * Determine which browser is used based on the provided user agent
	 * 
	 * @param userAgent
	 *            the user agent string
	 * @return the found browser or null if no browser found
	 */
	public Browser resolve(String userAgent);
}
