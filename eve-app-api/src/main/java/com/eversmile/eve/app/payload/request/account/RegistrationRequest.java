package com.eversmile.eve.app.payload.request.account;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {

    @Email
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
