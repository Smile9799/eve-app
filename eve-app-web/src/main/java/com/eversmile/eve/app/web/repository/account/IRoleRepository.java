package com.eversmile.eve.app.web.repository.account;

import com.eversmile.eve.app.web.model.account.AppRole;
import com.eversmile.eve.app.web.repository.IAppRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IRoleRepository extends IAppRepository<AppRole, Long> {

    Optional<AppRole> findByRoleName(String roleName);
}
