package org.springframework.browser.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.browser.Browser;
import org.springframework.web.context.request.RequestAttributes;

public class BrowserUtils {

	/**
	 * The name of the request attribute the current Browser is indexed by. The
	 * attribute name is 'currentBrowser'.
	 */
	public static final String CURRENT_BROWSER_ATTRIBUTE = "currentBrowser";

	/**
	 * Static utility method that extracts the current browser from the web
	 * request. Encapsulates the {@link HttpServletRequest#getAttribute(String)}
	 * lookup.
	 * 
	 * @param request
	 *            the servlet request
	 * @return the current browser, or null if no browser has been resolved for
	 *         the request
	 */
	public static Browser getCurrentBrowser(HttpServletRequest request) {
		return (Browser) request.getAttribute(CURRENT_BROWSER_ATTRIBUTE);
	}

	/**
	 * Static utility method that extracts the current browser from the web
	 * request. Encapsulates the {@link HttpServletRequest#getAttribute(String)}
	 * lookup. Throws a runtime exception if the current browser has not been
	 * resolved.
	 * 
	 * @param request
	 *            the servlet request
	 * @return the current browser
	 */
	public static Browser getRequiredCurrentBrowser(HttpServletRequest request) {
		Browser browser = getCurrentBrowser(request);
		if (browser == null) {
			throw new IllegalStateException("No current browser is set in this request and one is required - have you configured a BrowserResolvingHandlerInterceptor?");
		}
		return browser;
	}

	/**
	 * Static utility method that extracts the current browser from the request
	 * attributes map. Encapsulates the
	 * {@link HttpServletRequest#getAttribute(String)} lookup.
	 * 
	 * @param attributes
	 *            the request attributes
	 * @return the current browser, or null if no browser has been resolved for
	 *         the request
	 */
	public static Browser getCurrentBrowser(RequestAttributes attributes) {
		return (Browser) attributes.getAttribute(CURRENT_BROWSER_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
	}

}
