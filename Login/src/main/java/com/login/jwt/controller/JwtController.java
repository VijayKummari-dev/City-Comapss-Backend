package com.login.jwt.controller;

import com.login.jwt.entity.JwtRequest;
import com.login.jwt.entity.JwtResponse;
import com.login.jwt.service.JwtService;
import com.login.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private JwtUtil jwtutil;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }


}
