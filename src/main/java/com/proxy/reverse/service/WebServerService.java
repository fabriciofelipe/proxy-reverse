package com.proxy.reverse.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.api.Request;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Map.Entry;

@Service
@Slf4j
@Data
public class WebServerService
{
	private HttpClient httpClient;
	private String serverName;
	private Integer port;

	public WebServerService(String serverName, Integer port) throws Exception
	{
		this.httpClient = new HttpClient();
		this.serverName = serverName;
		this.port = port;
	}

	public ContentResponse getContentResponse(String target, HttpServletRequest request) throws Exception{

		log.info("target {}", target);

		if("GET".equals(request.getMethod()))
		{
			return doGet(target);
		}
		
		return doPost(target, request);
	}

	public ContentResponse doPost(String target, HttpServletRequest request) throws Exception
	{
		String contentURL = getContentURL();
		log.info("Content URL doPost {}", contentURL);

		Map<String, String[]> parameters = request.getParameterMap();
		Request postRequest = httpClient.POST(contentURL);
		for (Entry<String, String[]> parameter : parameters.entrySet())
		{
			postRequest = postRequest.param(parameter.getKey(), String.join(",", parameter.getValue()));
		}
		return postRequest.send();
	}

	public ContentResponse doGet(String target) throws Exception
	{
		String contentURL = getContentURL();
		log.info("Content URL doGet {}", contentURL);
		return httpClient.GET(contentURL);
	}

	public String getFQDN()
	{
		return serverName;
	}
	
	public String getContentURL()
	{
		return "http://" + getFQDN() + ":" + port;
	}
}
