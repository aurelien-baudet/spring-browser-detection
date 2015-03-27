package org.springframework.browser.resolver;

import javax.servlet.http.HttpServletRequest;

import org.springframework.browser.Browser;

public class UserAgentHttpHeaderDelegate implements HttpRequestBrowserResolver {

	private UserAgentBrowserResolver delegate;
	
	public UserAgentHttpHeaderDelegate(UserAgentBrowserResolver delegate) {
		super();
		this.delegate = delegate;
	}

	public Browser resolve(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent");
		return userAgent==null ? null : delegate.resolve(userAgent);
	}

}
