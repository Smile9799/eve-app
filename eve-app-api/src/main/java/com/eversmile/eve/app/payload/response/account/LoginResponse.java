package com.eversmile.eve.app.payload.response.account;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
public class LoginResponse {

    private String token;
    private List<String> roles;
}
