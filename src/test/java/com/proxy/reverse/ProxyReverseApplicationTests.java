package com.proxy.reverse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.AbstractConfigurableWebServerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProxyReverseApplicationTests {

	@Autowired
	private AbstractConfigurableWebServerFactory webServerFactory;

	@Test
	public void testSsl() {
		assertThat(this.webServerFactory.getSsl().isEnabled()).isTrue();
	}

}
