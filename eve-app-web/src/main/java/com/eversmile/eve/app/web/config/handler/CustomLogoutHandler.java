package com.eversmile.eve.app.web.config.handler;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

public class CustomLogoutHandler implements LogoutHandler {

    private final PersistentTokenRepository tokenRepository;

    public CustomLogoutHandler(PersistentTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // Delete the persistent token associated with the current user
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            tokenRepository.removeUserTokens(userDetails.getUsername());
        }

        // Delete the "Remember Me" cookie
        Cookie cookie = new Cookie("remember-me", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
