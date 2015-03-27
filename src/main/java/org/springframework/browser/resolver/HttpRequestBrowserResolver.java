package org.springframework.browser.resolver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.browser.Browser;

/**
 * Resolver that find which browser is used according to the received HTTP request
 * 
 * @author Aurélien Baudet
 *
 */
public interface HttpRequestBrowserResolver {
	/**
	 * Determine which browser is used based on what the client has sent in its
	 * request
	 * 
	 * @param request
	 *            the HTTP request
	 * @return the found browser or null if no browser found
	 */
	public Browser resolve(HttpServletRequest request);
}
