package org.springframework.browser.config;

import java.io.IOException;

import org.springframework.browser.resolver.ConfigurableBrowserResolver;
import org.springframework.browser.resolver.UserAgentBrowserResolver;
import org.springframework.browser.util.ConfigurationUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultConfig {
	@Bean
	public UserAgentBrowserResolver userAgentBrowserResolver() throws IOException {
		return new ConfigurableBrowserResolver(ConfigurationUtils.load());
	}
}
