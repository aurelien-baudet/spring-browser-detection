package org.springframework.browser.mvc;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.browser.Browser;
import org.springframework.browser.resolver.HttpRequestBrowserResolver;
import org.springframework.browser.resolver.PropertiesBrowserResolver;
import org.springframework.browser.resolver.UserAgentHttpHeaderDelegate;
import org.springframework.browser.util.BrowserUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * A Servlet 2.3 Filter that resolves the Browser that originated the web
 * request. The resolved Browser is exported as a request attribute under the
 * well-known name of {@link BrowserUtils#CURRENT_BROWSER_ATTRIBUTE}. Request
 * handlers such as @Controllers and views may then access the currentBrowser to
 * vary their control and rendering logic, respectively.
 * 
 * @author Aurélien Baudet
 */
public class BrowserResolverRequestFilter extends OncePerRequestFilter {

	private final HttpRequestBrowserResolver browserResolver;

	/**
	 * Create a browser resolving {@link Filter} that defaults to a
	 * {@link UserAgentHttpHeaderDelegate} implementation. The
	 * {@link UserAgentHttpHeaderDelegate} delegates the mapping to
	 * {@link PropertiesBrowserResolver} implementation.
	 * 
	 * @throws IOException
	 *             when property file couldn't be loaded
	 */
	public BrowserResolverRequestFilter() throws IOException {
		this(new UserAgentHttpHeaderDelegate(new PropertiesBrowserResolver()));
	}

	/**
	 * Create a browser resolving {@link Filter}.
	 * 
	 * @param browserResolver
	 *            the browser resolver to delegate to.
	 */
	public BrowserResolverRequestFilter(HttpRequestBrowserResolver browserResolver) {
		this.browserResolver = browserResolver;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		Browser browser = browserResolver.resolve(request);
		request.setAttribute(BrowserUtils.CURRENT_BROWSER_ATTRIBUTE, browser);
		filterChain.doFilter(request, response);
	}

}