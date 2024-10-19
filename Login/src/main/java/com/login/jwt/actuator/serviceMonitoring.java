package com.login.jwt.actuator;

import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;


@Component
@RestControllerEndpoint(id="monitor")
public class serviceMonitoring {
    @GetMapping
    public String status(){
        return "VijayUP";
    }
}
