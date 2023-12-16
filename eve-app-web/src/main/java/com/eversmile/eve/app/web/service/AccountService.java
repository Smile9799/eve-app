package com.eversmile.eve.app.web.service;

import com.eversmile.eve.app.web.common.AppConstant;
import com.eversmile.eve.app.web.model.account.*;
import com.eversmile.eve.app.web.payload.request.AppUserRequest;
import com.eversmile.eve.app.web.payload.request.RoleRequest;
import com.eversmile.eve.app.web.repository.account.IAppUserRoleRepository;
import com.eversmile.eve.app.web.repository.account.IRoleRepository;
import com.eversmile.eve.app.web.repository.account.IUserRepository;
import com.eversmile.eve.app.web.repository.finance.IAppGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Service
public class AccountService {

    @Autowired private IUserRepository userRepository;
    @Autowired private IRoleRepository roleRepository;
    @Autowired private IAppUserRoleRepository userRoleRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private IAppGroupRepository groupRepository;

    public void saveAppUser(AppUserRequest userRequest){
        AppUser user = mapToEntity(userRequest);
        AppRole role = roleRepository.findByRoleName(AppConstant.APP_ROLES.DEFAULT.name()).get();
        AppGroup group = groupRepository.findByGroupName(AppConstant.GROUP_TYPE.INDIVIDUAL.name()).get();
        AppUserRole userRole = AppUserRole.builder()
                .role(role)
                .isActive(true)
                .user(user)
                .uuid(UUID.randomUUID().toString())
                .validFrom(Instant.now())
                .build();
        AppUserGroup userGroup = AppUserGroup.builder()
                .appGroup(group)
                .uuid(UUID.randomUUID().toString())
                .validFrom(Instant.now())
                .users(Set.of(user))
                .build();
        user.setUserRoles(Set.of(userRole));
        user.setUserGroups(Set.of(userGroup));
        userRepository.save(user);
    }

    public boolean userExists(String email){
        return userRepository.existsByEmail(email);
    }

    public void createRole(RoleRequest roleRequest){
        AppRole appRole = AppRole.builder()
                .roleName(roleRequest.getRoleName())
                .isActive(roleRequest.isActive())
                .uuid(UUID.randomUUID().toString())
                .build();

        roleRepository.save(appRole);
    }

    private AppUser mapToEntity(final AppUserRequest userRequest){
        return AppUser.builder()
                .email(userRequest.getEmail())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .uuid(UUID.randomUUID().toString())
                .build();
    }
}
