package com.eversmile.eve.app.repository.user;

import com.eversmile.eve.app.model.user.AppUser;
import com.eversmile.eve.app.repository.IAppRepository;

import java.util.Optional;

public interface IAppUserRepository extends IAppRepository<AppUser,Long> {

    boolean existsAppUserByEmail(String email);
    Optional<AppUser> findAppUserByEmail(String email);
    Optional<AppUser> findAppUserByEmailAndUuid(String email, String uuid);
}
