package org.springframework.browser.config;

import java.io.IOException;

import org.springframework.browser.resolver.PropertiesBrowserResolver;
import org.springframework.browser.resolver.UserAgentBrowserResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultConfig {
	@Bean
	public UserAgentBrowserResolver userAgentBrowserResolver() throws IOException {
		return new PropertiesBrowserResolver();
	}
}
