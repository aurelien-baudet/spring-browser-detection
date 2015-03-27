package org.springframework.browser.resolver;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.browser.Browser;
import org.springframework.browser.config.DefaultConfig;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DefaultConfig.class)
public class PropertiesBrowserResolverTest {
	@Autowired
	PropertiesBrowserResolver resolver;
	
	@Test
	public void firefox() {
		Browser browser = resolver.resolve("Mozilla/5.0 (Android; Mobile; rv:13.0) Gecko/13.0 Firefox/13.0");
		Assert.assertNotNull("browser should not be null", browser);
		Assert.assertEquals("browser name should be firefox", "firefox", browser.getName());
		Assert.assertEquals("browser version should be 13.0", "13.0", browser.getVersion());
		Assert.assertEquals("browser vendor prefix should be -moz", "-moz", browser.getVendorPrefix());
	}
	
	@Test
	public void unknown() {
		Browser browser = resolver.resolve("Unknown browser");
		Assert.assertNull("browser should be null", browser);
	}
}
