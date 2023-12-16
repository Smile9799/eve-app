package com.eversmile.eve.app.web.repository.account;

import com.eversmile.eve.app.web.model.account.AppUser;
import com.eversmile.eve.app.web.repository.IAppRepository;

import java.util.Optional;

public interface IUserRepository extends IAppRepository<AppUser, Long> {

    boolean existsByEmail(String email);
    Optional<AppUser> findByEmail(String email);
}
