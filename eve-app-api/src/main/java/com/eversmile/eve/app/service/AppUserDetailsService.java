package com.eversmile.eve.app.service;

import com.eversmile.eve.app.model.user.AppRole;
import com.eversmile.eve.app.model.user.AppUser;
import com.eversmile.eve.app.repository.user.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired private IAppUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> appUser = userRepository.findAppUserByEmail(username);
        if(Objects.nonNull(appUser) && appUser.isPresent()){
            AppUser user = appUser.get();
            return buildUserDetails(user);
        }
        throw new UsernameNotFoundException("Could not found a user with specified username");
    }

    private UserDetails buildUserDetails(AppUser user){
        Set<AppRole> roles = user.getUserRoles();
        Collection<GrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
    }
}
