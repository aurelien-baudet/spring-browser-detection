package org.springframework.browser.mvc;

import org.springframework.browser.Browser;
import org.springframework.browser.util.BrowserUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * Spring MVC {@link WebArgumentResolver} that resolves @Controller
 * MethodParameters of type {@link Browser} to the value of the web request's
 * {@link BrowserUtils#CURRENT_BROWSER_ATTRIBUTE current device} attribute.
 * 
 * @author Aurélien Baudet
 */
public class BrowserWebArgumentResolver implements WebArgumentResolver {

	public Object resolveArgument(MethodParameter param, NativeWebRequest request) throws Exception {
		if (Browser.class.isAssignableFrom(param.getParameterType())) {
			return BrowserUtils.getCurrentBrowser(request);
		} else {
			return WebArgumentResolver.UNRESOLVED;
		}
	}

}