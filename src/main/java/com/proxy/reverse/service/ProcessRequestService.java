package com.proxy.reverse.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProcessRequestService {


    private WebServerService webServerService;

    public Optional<ContentResponse> processRequest(String host,HttpServletRequest request) throws Exception {
        if( host.equalsIgnoreCase("test1.localdomain")) {
            webServerService = new WebServerService("10.0.0.1",8000);
        } else {
            webServerService = new WebServerService("10.0.0.2",8000);
        }
        return Optional.ofNullable(webServerService.getContentResponse(host, request));
    }

}
