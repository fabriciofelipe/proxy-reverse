package com.proxy.reverse;

import com.proxy.reverse.service.WebServerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.AbstractConfigurableWebServerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;

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


	@Test
	public void testGetContentURL() throws Exception
	{
		WebServerService webServer = new WebServerService("10.0.0.2", 8000);
		String expected = "http://10.0.0.2:8000";

		String actual = webServer.getContentURL();

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void testGetContentResponse_RequestGetMethod() throws Exception
	{

		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		Mockito.when(request.getMethod()).thenReturn("GET");

		WebServerService server = Mockito.mock(WebServerService.class);
		Mockito.doCallRealMethod().when(server).getContentResponse(Mockito.any(), Mockito.any());

		server.getContentResponse(null, request);

		Mockito.verify(server, Mockito.times(1)).doGet(null);
	}
	@Test
	public void testGetContentResponse_RequestPostMethod() throws Exception
	{
		HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
		Mockito.when(request.getMethod()).thenReturn("POST");

		WebServerService server = Mockito.mock(WebServerService.class);
		Mockito.doCallRealMethod().when(server).getContentResponse(Mockito.any(), Mockito.any());

		server.getContentResponse(null, request);

		Mockito.verify(server, Mockito.times(1)).doPost(null, request);
	}
}
