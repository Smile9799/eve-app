package com.eversmile.eve.app.controller;

import com.eversmile.eve.app.model.user.AppUser;
import com.eversmile.eve.app.payload.request.account.LoginRequest;
import com.eversmile.eve.app.payload.request.account.RegistrationRequest;
import com.eversmile.eve.app.payload.response.account.AppUserResponse;
import com.eversmile.eve.app.payload.response.account.LoginResponse;
import com.eversmile.eve.app.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private AppUserService userService;

    @PostMapping("/register")
    public ResponseEntity<AppUserResponse> registerUser(@RequestBody RegistrationRequest request){
        AppUserResponse response = userService.registerUser(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest loginRequest){
        //ResponseCookie jwtCookie = userService.loginUser(loginRequest);
        return ResponseEntity.ok(userService.loginUser(loginRequest));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = userService.logoutUser();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .build();
    }
}
