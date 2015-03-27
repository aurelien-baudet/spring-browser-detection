package org.springframework.browser.mvc;

import org.springframework.browser.Browser;
import org.springframework.browser.util.BrowserUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * Spring MVC {@link HandlerMethodArgumentResolver} that resolves @Controller MethodParameters of type {@link Browser}
 * to the value of the web request's {@link BrowserUtils#CURRENT_BROWSER_ATTRIBUTE current device} attribute.
 * 
 * @author Aurélien Baudet
 */
public class BrowserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

	public boolean supportsParameter(MethodParameter parameter) {
		return Browser.class.isAssignableFrom(parameter.getParameterType());
	}

	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer modelAndViewContainer,
			NativeWebRequest request, WebDataBinderFactory binderFactory) throws Exception {
		return BrowserUtils.getCurrentBrowser(request);
	}

}
