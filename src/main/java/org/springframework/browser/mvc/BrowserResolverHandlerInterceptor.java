package org.springframework.browser.mvc;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.browser.Browser;
import org.springframework.browser.resolver.HttpRequestBrowserResolver;
import org.springframework.browser.resolver.PropertiesBrowserResolver;
import org.springframework.browser.resolver.UserAgentHttpHeaderDelegate;
import org.springframework.browser.util.BrowserUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * A Spring MVC interceptor that resolves the Browser that originated the web
 * request <i>before</i> any request handler is invoked. The resolved Browser is
 * exported as a request attribute under the well-known name of
 * {@link BrowserUtils#CURRENT_BROWSER_ATTRIBUTE}. Request handlers such as @Controllers
 * and views may then access the currentBrowser to vary their control and
 * rendering logic, respectively.
 * 
 * @author Aurélien Baudet
 */
public class BrowserResolverHandlerInterceptor extends HandlerInterceptorAdapter {

	private final HttpRequestBrowserResolver browserResolver;

	/**
	 * Create a browser resolving {@link HandlerInterceptor} that defaults to a
	 * {@link UserAgentHttpHeaderDelegate} implementation. The
	 * {@link UserAgentHttpHeaderDelegate} delegates the mapping to
	 * {@link PropertiesBrowserResolver} implementation.
	 * 
	 * @throws IOException
	 *             when property file couldn't be loaded
	 */
	public BrowserResolverHandlerInterceptor() throws IOException {
		this(new UserAgentHttpHeaderDelegate(new PropertiesBrowserResolver()));
	}

	/**
	 * Create a browser resolving {@link HandlerInterceptor}.
	 * 
	 * @param browserResolver
	 *            the browser resolver to delegate to in
	 *            {@link #preHandle(HttpServletRequest, HttpServletResponse, Object)}
	 *            .
	 */
	public BrowserResolverHandlerInterceptor(HttpRequestBrowserResolver browserResolver) {
		this.browserResolver = browserResolver;
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Browser browser = browserResolver.resolve(request);
		request.setAttribute(BrowserUtils.CURRENT_BROWSER_ATTRIBUTE, browser);
		return true;
	}

}