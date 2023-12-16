package com.eversmile.eve.app.repository.user;

import com.eversmile.eve.app.model.user.AppRole;
import com.eversmile.eve.app.repository.IAppRepository;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface IAppRoleRepository extends IAppRepository<AppRole, Long> {

    Optional<AppRole> findByRoleName(String name);
}
