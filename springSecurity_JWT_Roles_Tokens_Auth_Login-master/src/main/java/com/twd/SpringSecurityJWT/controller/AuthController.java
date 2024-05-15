package com.twd.SpringSecurityJWT.controller;

import com.twd.SpringSecurityJWT.dto.ReqRes;
import com.twd.SpringSecurityJWT.logout.BlackList;
import com.twd.SpringSecurityJWT.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ReqRes> signUp(@RequestBody ReqRes signUpRequest){
        return ResponseEntity.ok(authService.signUp(signUpRequest));
    }
    @PostMapping("/signin")
    public ResponseEntity<ReqRes> signIn(@RequestBody ReqRes signInRequest){
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }
    @PostMapping("/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes refreshTokenRequest){
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }
    @PostMapping("/logout")
    public ResponseEntity<ReqRes> logout(@RequestHeader("Authorization") String tokenHeader) {
        // Rimuovi "Bearer " dal token
        String token = tokenHeader.substring(7);
        // Chiama il metodo logout del servizio AuthService
        ReqRes response = authService.logout(token);
        // Restituisci la risposta con uno status code basato sulla risposta del servizio AuthService
        if (response.getStatusCode() == 200) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(response.getStatusCode()).body(response);
        }

    }
}
