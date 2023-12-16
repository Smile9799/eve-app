package com.eversmile.eve.app.service;

import com.eversmile.eve.app.common.AppConstants;
import com.eversmile.eve.app.common.AppHelper;
import com.eversmile.eve.app.common.ResponseMapper;
import com.eversmile.eve.app.middleware.filter.JwtUtils;
import com.eversmile.eve.app.model.user.AppRole;
import com.eversmile.eve.app.model.user.AppUser;
import com.eversmile.eve.app.payload.request.account.LoginRequest;
import com.eversmile.eve.app.payload.request.account.RegistrationRequest;
import com.eversmile.eve.app.payload.response.account.AppUserResponse;
import com.eversmile.eve.app.payload.response.account.LoginResponse;
import com.eversmile.eve.app.repository.user.IAppRoleRepository;
import com.eversmile.eve.app.repository.user.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AppUserService{

    @Autowired private IAppUserRepository userRepository;
    @Autowired private IAppRoleRepository roleRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtUtils jwtUtils;
    @Autowired private AppHelper appHelper;
    @Autowired private ResponseMapper responseMapper;

    public AppUserResponse registerUser(RegistrationRequest registrationRequest){
        AppRole appRole = roleRepository.findByRoleName(AppConstants.APP_DEFAULT_ROLE).orElse(null);
        AppUser appUser = AppUser.builder()
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .firstName(registrationRequest.getFirstName())
                .lastName(registrationRequest.getLastName())
                .uuid(appHelper.generateUuid())
                .userRoles(Set.of(appRole))
                .build();

        userRepository.save(appUser);

        return responseMapper.fromAppUser(appUser);
    }

    public LoginResponse loginUser(LoginRequest loginRequest){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> authorities = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return LoginResponse.builder()
                .token(jwtUtils.generateJwtToken(userDetails.getUsername(), authorities))
                .roles(authorities)
                .build();
    }

    public ResponseCookie logoutUser(){
        return jwtUtils.getCleanJwtCookie();
    }
}
