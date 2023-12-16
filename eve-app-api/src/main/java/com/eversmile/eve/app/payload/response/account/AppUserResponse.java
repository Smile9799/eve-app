package com.eversmile.eve.app.payload.response.account;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class AppUserResponse {

    private String email;
    private String firstName;
    private String lastName;
    private boolean isLocked;
    private boolean isActive;
    private String id;
    private LocalDateTime joinedAt;
}
