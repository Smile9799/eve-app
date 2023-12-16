package com.eversmile.eve.app.web.repository.finance;

import com.eversmile.eve.app.web.model.account.AppGroup;
import com.eversmile.eve.app.web.repository.IAppRepository;

import java.util.Optional;

public interface IAppGroupRepository extends IAppRepository<AppGroup, Long> {
    Optional<AppGroup> findByGroupName(String groupName);
}
