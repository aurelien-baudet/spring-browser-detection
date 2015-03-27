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
		Assert.assertEquals("browser vendor prefix should be -moz-", "-moz-", browser.getVendorPrefix());
	}
	
	@Test
	public void chrome() {
		Browser browser = resolver.resolve("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.110 Safari/537.36");
		Assert.assertNotNull("browser should not be null", browser);
		Assert.assertEquals("browser name should be chrome", "chrome", browser.getName());
		Assert.assertEquals("browser version should be 27.0.1453.110", "27.0.1453.110", browser.getVersion());
		Assert.assertEquals("browser vendor prefix should be -webkit-", "-webkit-", browser.getVendorPrefix());
	}
	
	@Test
	public void safari() {
		Browser browser = resolver.resolve("Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_5; fr-fr) AppleWebKit/525.18 (KHTML, like Gecko) Version/3.1.2 Safari/525.20.1");
		Assert.assertNotNull("browser should not be null", browser);
		Assert.assertEquals("browser name should be safari", "safari", browser.getName());
		Assert.assertEquals("browser version should be 525.20.1", "525.20.1", browser.getVersion());
		Assert.assertEquals("browser vendor prefix should be -webkit-", "-webkit-", browser.getVendorPrefix());
	}
	
	@Test
	public void ie() {
		Browser browser = resolver.resolve("Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.0; Trident/5.0)");
		Assert.assertNotNull("browser should not be null", browser);
		Assert.assertEquals("browser name should be internet explorer", "internet explorer", browser.getName());
		Assert.assertEquals("browser version should be 9", "9.0", browser.getVersion());
		Assert.assertEquals("browser vendor prefix should be -ms-", "-ms-", browser.getVendorPrefix());
	}
	
	@Test
	public void unknown() {
		Browser browser = resolver.resolve("Unknown browser");
		Assert.assertNull("browser should be null", browser);
	}
}
