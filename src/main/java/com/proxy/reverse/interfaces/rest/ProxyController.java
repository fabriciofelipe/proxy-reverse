package com.proxy.reverse.interfaces.rest;

import com.proxy.reverse.service.ProcessRequestService;
import lombok.RequiredArgsConstructor;
import org.eclipse.jetty.client.api.ContentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class ProxyController {

    private final ProcessRequestService processRequestService;

    @GetMapping("/test")
    public ResponseEntity<ContentResponse> getContentResult(@RequestHeader("Host") String host,
                                                            HttpServletRequest request) throws Exception {
        return processRequestService.processRequest(host,request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
