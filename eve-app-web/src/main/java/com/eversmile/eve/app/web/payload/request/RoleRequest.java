package com.eversmile.eve.app.web.payload.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequest {

    @NotEmpty(message = "role name is required")
    private String roleName;
    private boolean isActive;
}
